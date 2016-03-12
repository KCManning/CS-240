package manningk.bettercurve;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    DataManager dm = new DataManager(this);
    Card testCard;
    Deck testDeck;
    InterfaceComponents testInterface;
    LinearLayout srlLayoutView;

    final int MESSAGE_REQUEST = 1;
    static final String CARD_ID = "feh001";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_build_test_screen);
        testCard = Card.getTestCard();

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
            if(testDeck.getQty(i) == 0)
                addRow();
            else
                addRow(testDeck.getCard(i), testDeck.getQty(i));
        }
        testDeck.emptyDeck();
        //clearDeck();
    }

    public void btnCancelOnClick(View view) {
        testDeck.emptyDeck();
        //clearDeck();
        finish();
    }

    public void addRow() {
        //define and create a linear layout
        LinearLayout grpLayoutView = new LinearLayout(this);
        grpLayoutView.setOrientation(LinearLayout.HORIZONTAL);
        grpLayoutView.setGravity(Gravity.TOP | Gravity.LEFT);

        android.widget.LinearLayout.LayoutParams txtLayoutParams =
                new LinearLayout.LayoutParams(
                        350, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText txtName = new EditText(this);
        txtName.setText(testCard.getM_strName());
        txtName.setLayoutParams(txtLayoutParams);
        txtName.setFocusable(false);
        txtName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                buildCardInfo(); }});

        EditText txtCost = new EditText(this);
        txtCost.setVisibility(View.INVISIBLE);
        EditText txtQty = new EditText(this);
        txtQty.setText("0");
        txtQty.setVisibility(View.INVISIBLE);
        Button btnRaise = new Button(this);
        btnRaise.setVisibility(View.INVISIBLE);
        Button btnLower = new Button(this);
        btnLower.setVisibility(View.INVISIBLE);

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
                        350,
                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        android.widget.LinearLayout.LayoutParams smallLayoutParams =
                new LinearLayout.LayoutParams(90,
                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);


        // EditText txtName = (EditText) findViewById(R.id.txtName);
        EditText txtName = new EditText(this);
        txtName.setText(card.getM_strName());
        txtName.setLayoutParams(txtLayoutParams);
        txtName.setFocusable(false);
        txtName.setOnClickListener(new ImprovedListener(testCard) {
            public void onClick(View arg0) {
                buildCardInfo(card);
            }
        });

        //EditText txtCost = (EditText) findViewById(R.id.txtCost);
        EditText txtCost = new EditText(this);
        txtCost.setText(String.valueOf(card.getM_intCost()));
        txtCost.setLayoutParams(smallLayoutParams);
        txtCost.setFocusable(false);

        //EditText txtQty = (EditText) findViewById(R.id.txtQty);
        EditText txtQty = new EditText(this);
        txtQty.setText(String.valueOf(qty));
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
        addRow();
    }

    public void btnSaveDeckOnClick(View view) {
        testDeck.emptyDeck();
        //clearDeck();
        saveDeck();
    }

    public void saveDeck() {
        testDeck.emptyDeck();
        //clearDeck();
        ArrayList<View> arrAllCardData = getAllChildren(srlLayoutView);


        int intQty[] = new int[arrAllCardData.size() / 11];

        for (int i = 0; i < arrAllCardData.size(); i += 11) {
            EditText qty = (EditText) arrAllCardData.get(i + 6);
            testDeck.addCard(testCard.getTestCard(), Integer.parseInt(qty.getText().toString()));
        }

    }

    /*
    public void clearDeck() {
        if (testDeck.uniques() > 0)
            for (int i = testDeck.uniques(); i > 0; i--) {
                testDeck.removeCard(i);
            }
    }
*/
    public void buildCardInfo(Card card) {
        String passedName = "Card Details";
        Intent intent = new Intent(this, DetailsScreenActivity.class);
        //intent.putExtra(passedName, card.statsToArray()); //<-Adds info to be passed into the new activity, such as deck loading.
        startActivityForResult(intent, MESSAGE_REQUEST);
    }

    public void buildCardInfo() {

        //Intent intent = new Intent(this, DetailsScreenActivity.class);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == MESSAGE_REQUEST && resultCode == RESULT_OK)
        {
            String message = (String) data.getStringExtra(CARD_ID);

        }
    }

}
