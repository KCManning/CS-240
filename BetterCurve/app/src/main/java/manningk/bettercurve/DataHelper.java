package manningk.bettercurve;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class provides intermediary layer to the database.
 *
 * @author Kevin Manning
 * @version 2/25/2015
 */
public class DataHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "cardDB";
    private static final int DB_VERSION = 2;

    /**
     * Constructor.
     */
    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        setCards(context);
    }

    /**
     * Method to create the necessary database tables if the database doesn't already
     * exist on the device.
     *
     * @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStr = "CREATE TABLE Sets (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "SetCode TEXT, SetName TEXT)";
        db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE Cards (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "SetCode TEXT, FOREIGN KEY(SetCode) REFERENCES Sets(SetCode), "
                + "CardNumber TEXT NOT NULL, CardName TEXT NOT NULL, CardCost INTEGER NOT NULL, "
                + "StatNames TEXT NOT NULL, CardStats TEXT NOT NULL, Ability TEXT NOT NULL, Flavor TEXT NOT NULL)";
        db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE DeckCards (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DeckID INTEGER, FOREIGN KEY(DeckID) REFERENCES Decks(_id), "
                + "CardID INTEGER, FOREIGN KEY(CardID) REFERENCES Cards(_id), "
                + "Quantity INTEGER)";
        db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE Decks (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
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
            results = db.rawQuery("SELECT * FROM Decks", null);
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


    private void setCards(Context context) {
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase();
            AssetManager assetManager = context.getAssets();
            InputStream input = assetManager.open("Data.crd");

            BufferedReader inputFile = new BufferedReader(new InputStreamReader(input));

            String inputLines = inputFile.readLine();
            StringBuffer data = new StringBuffer();
            String sqlStr = "INSERT INTO Sets (SetName, SetCode) VALUES (Crystals, SE)";
            db.execSQL(sqlStr);
            sqlStr = "INSERT INTO Sets (SetName, SetCode) VALUES (Dark Prophecies, DP)";
            db.execSQL(sqlStr);
            sqlStr = "INSERT INTO Cards (CardName, SetCode, CardNumber, CardCost, StatNames," +
                    "CardStats, Ability, Flavor) VALUES (";

            while (inputLines != null) {
                //inputLines = inputLines.replace("\t", "\n");
                /*
                Name
                Set
                Number
                Cost
                Stats1
                Stats2
                Ability
                Flavor
                 */
                String name = inputLines.substring(0, inputLines.indexOf("\t"));
                inputLines = inputLines.replaceFirst(name + "\t", "");
                String set = inputLines.substring(0, inputLines.indexOf("\t"));
                inputLines = inputLines.replaceFirst(set + "\t", "");
                //set = db.rawQuery("SELECT SetCode FROM Sets WHERE SetName = " + set, null).toString();
                String number = inputLines.substring(0, inputLines.indexOf("\t"));
                inputLines = inputLines.replaceFirst(number + "\t", "");
                String cost = inputLines.substring(0, inputLines.indexOf("\t"));
                inputLines = inputLines.replaceFirst(cost + "\t", "");
                String statNames = inputLines.substring(0, inputLines.indexOf("\t"));
                inputLines = inputLines.replaceFirst(statNames + "\t", "");
                String stats = inputLines.substring(0, inputLines.indexOf("\t"));
                inputLines = inputLines.replaceFirst(stats + "\t", "");
                String ability = inputLines.substring(0, inputLines.indexOf("\t"));
                inputLines = inputLines.replaceFirst(ability + "\t", "");
                String flavor = inputLines;
                sqlStr += "\"" + name + "\"," + "\"" + set + "\"," + "\"" + number + "\","
                        + "\"" + cost + "\"," + "\"" + statNames + "\","
                        + "\"" + stats + "\"," + "\"" + ability + "\"," + "\"" + flavor + "\")";

                db.execSQL(sqlStr);
                inputLines = inputFile.readLine();

            }

            input.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
