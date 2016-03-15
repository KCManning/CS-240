package manningk.bettercurve;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/19/2016.
 */
public class BuildDeckActivity extends AppCompatActivity {

    DataManager dm;
    Card tempCard;
    Deck testDeck;
    LinearLayout srlLayoutView;

    final int MESSAGE_REQUEST = 1;
    static final String CARD_ID = "CardID";
    int cardID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dm = DataManager.getManager(this);

        setContentView(R.layout.activity_build_test_screen);
        tempCard = Card.getTestCard();

        srlLayoutView = new LinearLayout(this);

        //set vertical orientation
        srlLayoutView.setOrientation(LinearLayout.VERTICAL);
        srlLayoutView.setGravity(Gravity.TOP | Gravity.LEFT);

        ScrollView srlDeckHolder = (ScrollView) findViewById(R.id.srlDeckHolder);
        srlDeckHolder.addView(srlLayoutView);

        testDeck = dm.getTestDeck();
        dm.db.fillDeck(testDeck);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveDeck();
    }

    @Override
    protected void onResume() {
        super.onResume();
        srlLayoutView.removeAllViews();

        for (int i = 0; i < testDeck.getDeckList().size(); i++) {
            addRow(testDeck.getCard(i), testDeck.getQty(i));
        }
        //testDeck.emptyDeck();
        //clearDeck();
    }

    public void btnCancelOnClick(View view) {
        if (dm.deckID == -1)
            testDeck.emptyDeck();
        //clearDeck();
        finish();
    }

    public void addRow() {
        //define and create a linear layout
        LinearLayout grpLayoutView = new LinearLayout(getApplicationContext());
        grpLayoutView.setOrientation(LinearLayout.HORIZONTAL);
        grpLayoutView.setGravity(Gravity.TOP | Gravity.LEFT);

        android.widget.LinearLayout.LayoutParams txtLayoutParams =
                new LinearLayout.LayoutParams(
                        350, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText txtName = new EditText(getApplicationContext());
        txtName.setText(tempCard.getM_strName());
        txtName.setLayoutParams(txtLayoutParams);
        txtName.setFocusable(false);
        txtName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                buildCardInfo();
            }
        });

        EditText txtCost = new EditText(getApplicationContext());
        txtCost.setVisibility(View.INVISIBLE);
        EditText txtQty = new EditText(getApplicationContext());
        txtQty.setText("0");
        txtQty.setVisibility(View.INVISIBLE);
        Button btnRaise = new Button(getApplicationContext());
        btnRaise.setVisibility(View.INVISIBLE);
        Button btnLower = new Button(getApplicationContext());

        grpLayoutView.addView(txtName);
        grpLayoutView.addView(txtCost);
        grpLayoutView.addView(txtQty);
        grpLayoutView.addView(btnRaise);
        grpLayoutView.addView(btnLower);

        srlLayoutView.addView(grpLayoutView);
    }

    public void addRow(Card card, int qty) {
        android.widget.LinearLayout.LayoutParams txtLayoutParams =
                new LinearLayout.LayoutParams(
                        700,
                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        android.widget.LinearLayout.LayoutParams smallLayoutParams =
                new LinearLayout.LayoutParams(180,
                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);


        // EditText txtName = (EditText) findViewById(R.id.txtName);
        EditText txtName = new EditText(this);
        txtName.setText(card.getM_strName());
        txtName.setLayoutParams(txtLayoutParams);
        txtName.setFocusable(false);
        txtName.setOnClickListener(new ImprovedListener(tempCard) {
            public void onClick(View arg0) {
                //buildCardInfo(card);
            }
        });

        //EditText txtCost = (EditText) findViewById(R.id.txtCost);
        EditText txtCost = new EditText(this);
        txtCost.setText(Integer.toString(card.getM_intCost()));
        txtCost.setLayoutParams(smallLayoutParams);
        txtCost.setFocusable(false);

        //EditText txtQty = (EditText) findViewById(R.id.txtQty);
        EditText txtQty = new EditText(this);
        txtQty.setText(Integer.toString(qty));
        txtQty.setLayoutParams(smallLayoutParams);
        txtQty.setFocusable(false);

        //define and create a linear layout

        LinearLayout grpLayoutView = new LinearLayout(this);
        grpLayoutView.setOrientation(LinearLayout.HORIZONTAL);
        grpLayoutView.setGravity(Gravity.TOP | Gravity.LEFT);


        Button btnRaise = new Button(this);
        btnRaise.setText("+");
        btnRaise.setLayoutParams(smallLayoutParams);
        btnRaise.setOnClickListener(new ImprovedListener(txtQty) {
            public void onClick(View arg0) {
                int current = Integer.parseInt(txtQty.getText().toString());
                txtQty.setText(String.valueOf(current + 1));
            }
        });

        Button btnLower = new Button(this);
        btnLower.setText("-");
        btnLower.setLayoutParams(smallLayoutParams);
        btnLower.setOnClickListener(new ImprovedListener(txtQty, grpLayoutView) {
            public void onClick(View arg0) {
                int current = Integer.parseInt(txtQty.getText().toString());
                if (current > 1)
                    txtQty.setText(String.valueOf(current - 1));
                else {
                    srlLayoutView.removeView(grpLayoutView);

                }

            }
        });

        grpLayoutView.addView(txtName);
        grpLayoutView.addView(txtCost);
        grpLayoutView.addView(txtQty);
        grpLayoutView.addView(btnRaise);
        grpLayoutView.addView(btnLower);

        srlLayoutView.addView(grpLayoutView);

    }

    public void btnAddCardOnClick(View view) {
        // addRow(tempCard, 2);
        buildCardInfo();
    }

    public void btnSaveDeckOnClick(View view) {

        testDeck.emptyDeck();
        //clearDeck();

        saveDeck();
        if (dm.getDeckName(dm.deckID) == null)
            newName();

    }

    public void saveDeck() {
        testDeck.emptyDeck();
        //clearDeck();
        ArrayList<View> arrAllCardData = getAllChildren(srlLayoutView);


        int intQty[] = new int[arrAllCardData.size() / 11];

        for (int i = 0; i < arrAllCardData.size(); i += 11) {
            EditText name = (EditText) arrAllCardData.get(i + 2);
            EditText qty = (EditText) arrAllCardData.get(i + 6);
            dm.db.getCard(tempCard, name.getText().toString());
            if (dm.deckID == -1) {

                testDeck.addCard(tempCard, Integer.parseInt(qty.getText().toString()));
                if (i >= arrAllCardData.size() - 11)
                    dm.addDeck(testDeck);
                dm.deckID = dm.getDeckCount() - 1;
            } else
                dm.addCardToDeck(dm.deckID, tempCard, Integer.parseInt(qty.getText().toString()));

        }

    }

    /*
    public void clearDeck() {
        if (testDeck.uniques() > 0)
            for (int i = testDeck.uniques(); i > 0; i--) {
                testDeck.removeCard(i);
            }
    }

    public void buildCardInfo(Card card) {
        String passedName = "Card Details";

        //intent.putExtra(passedName, card.statsToArray()); //<-Adds info to be passed into the new activity, such as deck loading.
        startActivityForResult(intent, MESSAGE_REQUEST);
    }
*/
    public void buildCardInfo() {

        Intent intent = new Intent(this, CardListActivity.class);
        startActivityForResult(intent, MESSAGE_REQUEST);
    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MESSAGE_REQUEST && resultCode == RESULT_OK) {
            cardID = Integer.parseInt((String) data.getStringExtra(CARD_ID));
            if (cardID == CardDetailFragment.card.getM_ID()) {
                tempCard = CardDetailFragment.card;
                testDeck.addCard(tempCard);
            }

        }
    }


    private void newName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = input.getText().toString();
                dm.setDeckName(dm.deckID, name);
                dm.backupDecks();
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }

        });

        builder.show();
    }


}
