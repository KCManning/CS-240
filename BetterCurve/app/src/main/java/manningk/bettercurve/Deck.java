package manningk.bettercurve;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 1/16/2016.
 */
public class Deck
{
    private static Deck deck;
    private ArrayList<Card> deckList;
    private int quantities[];
    private int nextCardID;


    private Deck()
    {
        deckList = new ArrayList<>();
        quantities = new int[deckList.size()];
        deckList.add(Card.getTestCard());
        nextCardID++;
    }

    public static Deck getDeck()
    {
        if(deck == null)
            deck = new Deck();

        return deck;
    }

    public List<Card> getDeckList()
    {
        return deckList;
    }

    public Card getCard(int id)
    {
        return deckList.get(id);
    }

    public int uniques()
    {
        ArrayList<Card> tempDeck = new ArrayList<Card>();

        for(int i = 0; i < deckList.size(); i++)
        {
            if(!tempDeck.contains(deckList.get(i)))
                tempDeck.add(deckList.get(i));
        }

        return tempDeck.size();
    }

    public int getDeckSize()
    {
        int deckSize = 0;
        for(int i = 0; i < quantities.length; i++)
            deckSize += quantities[i];

        return deckSize;
    }

}
