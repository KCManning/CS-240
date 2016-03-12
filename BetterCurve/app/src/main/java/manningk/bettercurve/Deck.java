package manningk.bettercurve;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Created by Kevin on 1/16/2016.
 *
 */
public class Deck {
    private static Deck deck;
    public int id;
    private String deckName;
    private String gameName;
    private String deckType;
    private ArrayList<Card> deckList;
    private ArrayList<String> quantities;


    public Deck() {
        deckList = new ArrayList<>();
        quantities = new ArrayList<>();
        deckType = "Shadow Era";
    }

    public void setDeckName(String name)
    {
        deckName = name;
    }

    public static Deck getDeck(Context context) {
        if (deck == null)
            deck = new Deck();

        return deck;
    }

    public List<Card> getDeckList() {
        return deckList;
    }

    public Card getCard(int id) {
        return deckList.get(id);
    }

    public int getQty(int id) {
        return Integer.parseInt(quantities.get(id));
    }

    public void addCard(Card card) {
        deckList.add(card);
        quantities.add("1");
    }

    public void addCard(Card card, int qty) {
        deckList.add(card);
        quantities.add(Integer.toString(qty));
    }

    public void removeCard(Card card) {
        int index = deckList.indexOf(card);

        deckList.remove(index);
        quantities.remove(index);
    }

    public void removeCard(int index) {
        index--;
        deckList.remove(index);
        quantities.remove(index);
    }

    public int uniques() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();

        for (int i = 0; i < deckList.size(); i++) {
            if (!tempDeck.contains(deckList.get(i)))
                tempDeck.add(deckList.get(i));
        }

        return tempDeck.size();
    }

    public int getDeckSize() {
        int deckSize = 0;
        for (int i = 0; i < quantities.size(); i++)
            deckSize += Integer.parseInt(quantities.get(i));

        return deckSize;
    }

    public String getDeckName() {
        return deckName;
    }

    public void emptyDeck() {
        if (deckList.size() > 0)
            for (int i = deckList.size(); i >= 1; i--)
                removeCard(i);
    }

    public String getGameName() {
        return gameName;
    }

    public String getDeckType() {
        return deckType;
    }
}
