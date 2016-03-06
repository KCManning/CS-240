package manningk.bettercurve;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Kevin on 2/12/2016.
 */
public class DataManager {

    private ArrayList<Deck> decks;
    private static DataManager dm;
    private DataHelper db;
    private Context context;


    public DataManager(Context context) {
        decks = new ArrayList<>();
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

    public Deck getDeck(int id) {  return decks.get(id); }

    public Deck getTestDeck() { return decks.get(0).getDeck(context);}

    public String getDeckName (int id)
    {
        Deck deck = getDeck(id);
        return deck.getDeckName();
    }

}
