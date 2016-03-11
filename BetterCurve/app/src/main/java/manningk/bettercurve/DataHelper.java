package manningk.bettercurve;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * This class provides intermediary layer to the database.
 *
 * @author Kevin Manning
 * @version 2/25/2015
 */
public class DataHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "cardDB";
    private static final int DB_VERSION = 1;

    /**
     * Constructor.
     */
    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Method to create the necessary database tables if the database doesn't already
     * exist on the device.
     *
     * @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStr = "CREATE TABLE Sets (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "SetCode TEXT, SetName TEXT)";
       db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE Cards (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "FOREIGN KEY(SetCode) REFERENCES Sets(SetCode),"
                + "CardNumber INTEGER, CardName TEXT, StatNames TEXT,"
                + "CardStats TEXT)";
       db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE DeckCards (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "FOREIGN KEY(DeckID) REFERENCES Cards(DeckID),"
                + "FOREIGN KEY(CardID) REFERENCES Decks(CardIF),"
                + "CardNumber INTEGER,)";
        db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE Decks (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "DeckName TEXT)";
       db.execSQL(sqlStr);
    }

    /**
     * Method used to upgrade an existing database on a device.
     *
     * @param db         the database
     * @param oldVersion the old version number
     * @param newVersion the new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public synchronized boolean getDecks(ArrayList<Deck> decks) {
        SQLiteDatabase db = null;
        Cursor results = null;
        try {
            db = this.getReadableDatabase();
            results = db.rawQuery("SELECT * FROM Client", null);
            if (results.moveToFirst()) {
                do {
                    int id = (int) results.getLong(results.getColumnIndex("_id"));
                    String deckName = results.getString(results.getColumnIndex("DeckName"));
                    Deck d = new Deck();
                    d.id = id;
                    d.setDeckName(deckName);
                    getCards(d);
                    decks.add(d);
                } while (results.moveToNext());
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (results != null && !results.isClosed()) {
                results.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

    }

    public synchronized boolean getCards(Deck d) {

        SQLiteDatabase db = null;
        Cursor results = null;


        try {
            db = this.getReadableDatabase();
            results = db.rawQuery("SELECT * FROM DeckCards WHERE deckName = " + d.id, null);
            if (results.moveToFirst()) {
                do {
                    int id = (int) results.getLong(results.getColumnIndex("_id"));
                    String setCode = results.getString(results.getColumnIndex("SetCode"));
                    int cardNumber = (int) results.getLong(results.getColumnIndex("CardNumber"));
                    String cardName = results.getString(results.getColumnIndex("CardName"));
                    int cost = (int) results.getLong(results.getColumnIndex("CardCost"));
                    String flavor = results.getString(results.getColumnIndex("CardFlavor"));
                    String statNames = results.getString(results.getColumnIndex("statNames"));
                    String cardStats = results.getString(results.getColumnIndex("cardStats"));


                    Card c = new Card(setCode, cardNumber, cardName, cost, flavor, cardStats, statNames);


                    d.addCard(c);
                } while (results.moveToNext());

            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (results != null && !results.isClosed()) {
                results.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }


    private void setCards(SQLiteDatabase db)
    {
        try {
            FileReader fileReader = new FileReader("Data.crd");
            BufferedReader inputFile = new BufferedReader(fileReader);

            String inputLines = inputFile.readLine();
            StringBuffer data = new StringBuffer();

            while (inputLines != null)
            {
                data.append(inputLines + "\n");
                inputLines = inputFile.readLine();
            }

            String sqlStr = "INSERT INTO TABLE Cards (SetCode, )"
                    +"VALUES" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, SetCode TEXT, SetName TEXT)";
            db.execSQL(sqlStr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
