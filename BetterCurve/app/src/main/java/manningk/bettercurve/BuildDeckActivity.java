package manningk.bettercurve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Kevin on 1/19/2016.
 */
public class BuildDeckActivity extends AppCompatActivity {

    Card testCard;
    Deck testDeck;
    InterfaceComponents testInterface;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_build_screen);
        //setContentView(R.layout.activity_build_test_screen);

        testCard = Card.getTestCard();

        //testInterface.makeTestInterface(this, testCard);

        //EditText txtName = (EditText) findViewById(R.id.txtName);
        //txtName.setText(testCard.getM_strName());

       // EditText txtCost = (EditText) findViewById(R.id.txtCost);
       // txtCost.setText(String.valueOf(testCard.getM_intCost()));

       // EditText txtQty = (EditText) findViewById(R.id.txtQty);
       // txtQty.setText(String.valueOf(1));
        //setContentView(R.layout.activity_build_screen);
    }

    public void btnCancelOnClick (View view)
    {

        finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra() <-Adds info to be passed into the new activity, such as deck loading.
       // startActivity(intent);
    }

    public void btnAddCardOnClick(View view)
    {
        /*EditText editText = new EditText(this.getApplicationContext());
        editText.
*/
    }

}
