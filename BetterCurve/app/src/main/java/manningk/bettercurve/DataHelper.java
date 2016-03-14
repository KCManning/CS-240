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
import java.util.List;

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
                + "SetName TEXT, SetCode TEXT)";
        db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE Cards (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "SetCode TEXT NOT NULL, CardNumber INTEGER NOT NULL, CardName TEXT NOT NULL, " +
                "CardCost INTEGER NOT NULL, StatNames TEXT NOT NULL, CardStats TEXT NOT NULL, " +
                "Ability TEXT NOT NULL, Flavor TEXT NOT NULL, FOREIGN KEY(SetCode) REFERENCES Sets(SetCode))";
        db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE DeckCards (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DeckID INTEGER NOT NULL, CardID INTEGER NOT NULL, Quantity INTEGER NOT NULL, "
                + "FOREIGN KEY(DeckID) REFERENCES Decks(_id), FOREIGN KEY(CardID) REFERENCES Cards(_id))";
        db.execSQL(sqlStr);
        sqlStr = "CREATE TABLE Decks (_id INTEGER PRIMARY KEY AUTOINCREMENT, DeckName TEXT)";
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
                    fillDeck(d);
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

    public synchronized boolean getAllCards(List<String> list) {
        SQLiteDatabase db = null;
        Cursor results = null;
        try {
            db = this.getReadableDatabase();
            results = db.rawQuery("SELECT CardName, CardCost FROM Cards", null);
            if (results.moveToFirst()) {
                do {
                    String cardName = results.getString(results.getColumnIndex("CardName"));
                    int cost = (int) results.getLong(results.getColumnIndex("CardCost"));
                    list.add(cardName + ", Cost: " + cost);

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

    public synchronized boolean getCard(Card c, String name) {
        SQLiteDatabase db = null;
        Cursor results = null;

        try {
            db = this.getReadableDatabase();
            results = db.rawQuery("SELECT * FROM Cards WHERE CardName = " + name, null);
            if (results.moveToFirst()) {

                    int id = (int) results.getLong(results.getColumnIndex("_id"));
                    String setCode = results.getString(results.getColumnIndex("SetCode"));
                    int cardNumber = (int) results.getLong(results.getColumnIndex("CardNumber"));
                    String cardName = results.getString(results.getColumnIndex("CardName"));
                    int cost = (int) results.getLong(results.getColumnIndex("CardCost"));
                    String statNames = results.getString(results.getColumnIndex("StatNames"));
                    String cardStats = results.getString(results.getColumnIndex("CardStats"));
                    String ability = results.getString(results.getColumnIndex("Ability"));
                    String flavor = results.getString(results.getColumnIndex("Flavor"));


                    c = new Card(setCode, cardNumber, cardName, cost, cardStats, statNames, ability, flavor);
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

    public synchronized boolean getCard(int id) {
        SQLiteDatabase db = null;
        Cursor results = null;

        try {
            db = this.getReadableDatabase();
            results = db.rawQuery("SELECT * FROM Cards WHERE _id = " + id, null);
            if (results.moveToFirst()) {

                int nextid = (int) results.getLong(results.getColumnIndex("_id"));
                String setCode = results.getString(results.getColumnIndex("SetCode"));
                int cardNumber = (int) results.getLong(results.getColumnIndex("CardNumber"));
                String cardName = results.getString(results.getColumnIndex("CardName"));
                int cost = (int) results.getLong(results.getColumnIndex("CardCost"));
                String statNames = results.getString(results.getColumnIndex("StatNames"));
                String cardStats = results.getString(results.getColumnIndex("CardStats"));
                String ability = results.getString(results.getColumnIndex("Ability"));
                String flavor = results.getString(results.getColumnIndex("Flavor"));


                //CardDetailFragment.card = new Card(setCode, cardNumber, cardName, cost, cardStats, statNames, ability, flavor);
                CardDetailFragment.card = new Card(setCode, cardNumber, cardName, cost, cardStats, statNames, ability, flavor);
                CardDetailFragment.card.setM_ID(nextid);
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

    public synchronized boolean fillDeck(Deck d) {

        SQLiteDatabase db = null;
        Cursor results = null;
        Cursor cardIDs = null;


        try {
            db = this.getReadableDatabase();
            results = db.rawQuery("SELECT CardID FROM DeckCards WHERE DeckID = " + d.id, null);
            if (results.moveToFirst()) {
                do {
                    cardIDs = db.rawQuery("SELECT * FROM Cards WHERE _id = "
                            + (int) results.getLong(results.getColumnIndex("_id")), null);

                    int id = (int) cardIDs.getLong(results.getColumnIndex("_id"));
                    String setCode = cardIDs.getString(results.getColumnIndex("SetCode"));
                    int cardNumber = (int) cardIDs.getLong(results.getColumnIndex("CardNumber"));
                    String cardName = cardIDs.getString(results.getColumnIndex("CardName"));
                    int cost = (int) cardIDs.getLong(results.getColumnIndex("CardCost"));
                    String statNames = cardIDs.getString(results.getColumnIndex("StatNames"));
                    String cardStats = cardIDs.getString(results.getColumnIndex("CardStats"));
                    String ability = cardIDs.getString(results.getColumnIndex("Ability"));
                    String flavor = cardIDs.getString(results.getColumnIndex("CardFlavor"));


                    Card c = new Card(setCode, cardNumber, cardName, cost, cardStats, statNames, ability, flavor);


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
            if (isDBEmpty(db)) {
                AssetManager assetManager = context.getAssets();
                InputStream input = assetManager.open("Data.crd");

                BufferedReader inputFile = new BufferedReader(new InputStreamReader(input));

                String inputLines = inputFile.readLine();
                StringBuffer data = new StringBuffer();
                String sqlStr = "INSERT INTO Sets (SetName, SetCode) VALUES (\"Crystals\", \"SE\")";
                db.execSQL(sqlStr);
                sqlStr = "INSERT INTO Sets (SetName, SetCode) VALUES (\"Dark Prophecies\", \"DP\")";
                db.execSQL(sqlStr);


                while (inputLines != null) {
                    sqlStr = "INSERT INTO Cards (CardName, SetCode, CardNumber, CardCost, StatNames," +
                            "CardStats, Ability, Flavor) VALUES (";
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

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean isDBEmpty(SQLiteDatabase db) {

        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM Cards", null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt(0) == 0);
        }
        cur.close();

        return empty;
    }
}
