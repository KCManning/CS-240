package manningk.bettercurve;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Kevin on 2/12/2016.
 */
public class DataManager {

    private ArrayList<Deck> decks;
    private static DataManager dm;

    public DataManager(Context context) {
        decks = new ArrayList<>();
    }

    public static DataManager getManager(Context context) {
        if (dm == null)
            dm = new DataManager(context);

        return dm;
    }

    public int getDeckCount() {
        return decks.size();
    }

    public Deck getDeck(int id) {  return decks.get(id); }

    public String[] getDeckInformation (int id)
    {
        Deck deck = getDeck(id);
        String[] info = new String[3];

        info[0] = deck.getDeckName();
        info[1] = deck.getGameName();
        info[2] = deck.getDeckType();

        return info;
    }

}
