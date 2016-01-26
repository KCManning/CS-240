package manningk.bettercurve;

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

/**
 * Created by Kevin on 1/19/2016.
 */
public class BuildDeckActivity extends AppCompatActivity {

    Card testCard;
    Deck testDeck;
    InterfaceComponents testInterface;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //Testing procedural layout generation
//        //set the title bar text
//        this.setTitle("Deckbuilder");
//
//        //define and create a linear layout
//        LinearLayout mainLayout = new LinearLayout(this);
//
//        //set vertical orientation
//        mainLayout.setOrientation(LinearLayout.VERTICAL);
//
//        TextView txtInfo = new TextView(this);
//        txtInfo.setText("Hello World Android !");
//        txtInfo.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
//        txtInfo.setTextSize(20);
//        txtInfo.setTextColor(Color.GREEN);
//        //txtInfo.setTextColor(Color.rgb(0, 255, 0));
//
//        Button btnClick = new Button(this);
//        //define the button caption
//        btnClick.setText("Click me !");
//
//        mainLayout.addView(txtInfo);
//        mainLayout.addView(btnClick);
//
//        //attach the layout to the Activity display
//        this.setContentView(mainLayout);
//
//        mainLayout.setGravity(Gravity.BOTTOM);
//
//        //define the LayoutParams for the TextView
////width - equal to the content
////height - equal to the content
//        android.widget.LinearLayout.LayoutParams txtLayoutParams =
//                new LinearLayout.LayoutParams(
//                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
//                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
////set the LayoutParams
//        txtInfo.setLayoutParams(txtLayoutParams);
//
////define the LayoutParams for the Button
////width - 120 px
////height - equal to the content
//        android.widget.LinearLayout.LayoutParams btnLayoutParams =
//                new LinearLayout.LayoutParams(
//                        120,
//                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
////set the LayoutParams
//        btnClick.setLayoutParams(btnLayoutParams);

        //setContentView(R.layout.activity_build_screen);
        setContentView(R.layout.activity_build_test_screen);

//        //Adding a scrollview
//        ScrollView deckContainer = new ScrollView(this);
//
//        //define the LayoutParams for the Button
////width - 120 px
////height - equal to the content
//        android.widget.LinearLayout.LayoutParams deckContainerParams =
//                new LinearLayout.LayoutParams(
//                        android.view.ViewGroup.LayoutParams.MATCH_PARENT,
//                        android.view.ViewGroup.LayoutParams.MATCH_PARENT);
////set the LayoutParams
//        deckContainer.setLayoutParams(deckContainerParams);


        testCard = Card.getTestCard();

        //testInterface.makeTestInterface(this, testCard);

        android.widget.LinearLayout.LayoutParams txtLayoutParams =
                new LinearLayout.LayoutParams(
                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);


        // EditText txtName = (EditText) findViewById(R.id.txtName);
        EditText txtName = new EditText(this);
        txtName.setText(testCard.getM_strName());
        txtName.setLayoutParams(txtLayoutParams);

        //EditText txtCost = (EditText) findViewById(R.id.txtCost);
        EditText txtCost = new EditText(this);
        txtCost.setText(String.valueOf(testCard.getM_intCost()));
        txtCost.setLayoutParams(txtLayoutParams);

        //EditText txtQty = (EditText) findViewById(R.id.txtQty);
        EditText txtQty = new EditText(this);
        txtQty.setText(String.valueOf(1));
        txtQty.setLayoutParams(txtLayoutParams);

        //define and create a linear layout
        LinearLayout srlLayoutView = new LinearLayout(this);


        //set vertical orientation
        srlLayoutView.setGravity(Gravity.TOP | Gravity.LEFT);

        ScrollView srlDeckHolder = (ScrollView) findViewById(R.id.srlDeckHolder);
        srlDeckHolder.addView(srlLayoutView);

        android.widget.LinearLayout.LayoutParams btnLayoutParams =
                new LinearLayout.LayoutParams(50,
                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btnRaise = new Button(this);
        btnRaise.setText("+");
        btnRaise.setLayoutParams(btnLayoutParams);
        Button btnLower = new Button(this);
        btnLower.setText("-");
        btnLower.setLayoutParams(btnLayoutParams);
        Button btnAdd = new Button(this);
        btnAdd.setText("+");
        btnAdd.setLayoutParams(btnLayoutParams);

        srlLayoutView.addView(txtName);
        srlLayoutView.addView(txtCost);
        srlLayoutView.addView(txtQty);
        srlLayoutView.addView(btnRaise);
        srlLayoutView.addView(btnLower);
        srlLayoutView.addView(btnAdd);

    }

    public void btnCancelOnClick(View view) {

        finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra() <-Adds info to be passed into the new activity, such as deck loading.
        // startActivity(intent);
    }

    public void btnAddCardOnClick(View view) {
        /*EditText editText = new EditText(this.getApplicationContext());
        editText.
*/
    }

}
