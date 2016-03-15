package manningk.bettercurve;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Kevin on 2/12/2016.
 */
public class DataManager {

    private ArrayList<Deck> decks;
    private static DataManager dm;
    public DataHelper db;
    private Context context;
    public int deckID;


    public DataManager(Context context) {
        decks = new ArrayList<>();
        db = new DataHelper(context);
        db.getDecks(decks);
    }

    /**
     * Singleton implementation - returns the single instance of
     * the DataManager class.
     *
     * @param context the application context
     */
    public static DataManager getManager(Context context) {
        if (dm == null)
            dm = new DataManager(context);

        return dm;
    }

    public int getDeckCount() {
        return decks.size();
    }

    public Deck getDeck(int id) {
        return decks.get(id);
    }

    public Deck getTestDeck() {
        return Deck.getDeck(context);
    }

    public String getDeckName(int id) {
        if(id == -1)
            return null;
        else {
            Deck deck = getDeck(id);
            return deck.getDeckName();
        }
    }

    public void addDeck(Deck d)
    {
        decks.add(d);
    }
    public void addCardToDeck(int id, Card c, int qty)
    {
        Deck tempDeck = decks.get(id);
        tempDeck.addCard(c, qty);
        decks.set(id, tempDeck);
    }

    public void setDeckName(int id, String name)
    {
        Deck tempDeck = decks.get(id);
        tempDeck.setDeckName(name);
        decks.set(id, tempDeck);
    }
 public void backupDecks()
 {
     db.backupDecks(decks);
 }

}
