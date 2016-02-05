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
    private ArrayList<String> quantities;
    private int nextCardID;


    private Deck()
    {
        deckList = new ArrayList<>();
        quantities = new ArrayList<>();
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

    public void addCard(Card card)
    {
        deckList.add(card);
        quantities.add("1");
    }

    public void addCard(Card card, int qty)
    {
        deckList.add(card);
        quantities.add(Integer.toString(qty));
    }

    public void removeCard(Card card)
    {
        int index = deckList.indexOf(card);

        deckList.remove(index);
        quantities.remove(index);
    }

    public void removeCard(int index)
    {
        deckList.remove(index);
        quantities.remove(index);
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
        for(int i = 0; i < quantities.size(); i++)
            deckSize += Integer.parseInt(quantities.get(i));

        return deckSize;
    }

}
