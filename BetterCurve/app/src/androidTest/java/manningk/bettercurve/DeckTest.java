package manningk.bettercurve;

import android.test.AndroidTestCase;

import java.util.List;

/**
 * Created by Kevin on 1/27/2016.
 * Implementation based off of in-class project
 */
public class DeckTest extends AndroidTestCase {

    Deck d;

    public void setUp() throws Exception {
        super.setUp();
        d = Deck.getDeck();
    }

    public void testGetDeckList() throws Exception {
        List<Card> list = d.getDeckList();
        assertNotNull("getDeckList failed", list);
    }

    //Unknown error cause
/*
        public void testValidGetCard() throws Exception {
            int intStats[] = {3, 5};
            String strStatNames[] = {"Defense", "Durability"};
            Card card = new Card("Make Believe", "Fh", 002, "False Defense", 7, "Please Work",
                    "I AM.", intStats, strStatNames);
            d.addCard(card);
           // Card c = d.getCard(card.getM_strName());
            assertNotNull("getCard failed", c);
        }
*/

    public void testInvalidGetCard() throws Exception {
        Card c70 = d.getCard(70);
        assertNull("getCard failed", c70);
    }

    public void testValidAddCard() throws Exception {
        int size1 = d.getDeckList().size();
        int intStats[] = {3, 5};
        String strStatNames[] = {"Defense", "Durability"};
        Card card = new Card("Make Believe", "Fh", 002, "False Defense", 7, "Please Work",
                "I AM.", intStats, strStatNames);
        int size2 = d.getDeckList().size();
        assertTrue("addCard failed", size2 - size1 == 1);
    }


    public void testInvalidAddCard() throws Exception {
        int size1 = d.getDeckList().size();
        Card c1 = null;
        d.addCard(c1);
        int size2 = d.getDeckList().size();
        assertTrue("addCard failed", size2 - size1 == 0);
    }


    public void testValidDeleteCard() throws Exception {
        int size1 = d.getDeckList().size();
        d.removeCard(0);
        int size2 = d.getDeckList().size();
        assertTrue("deleteCard failed", size1 - size2 == 1);
    }

    public void testInvalidDeleteCard() throws Exception {
        int size1 = d.getDeckList().size();
        d.removeCard(10);
        int size2 = d.getDeckList().size();
        assertTrue("deleteCard failed", size1 - size2 == 0);
    }



}
