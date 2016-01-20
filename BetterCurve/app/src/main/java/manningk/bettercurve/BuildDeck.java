package manningk.bettercurve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Kevin on 1/19/2016.
 */
public class BuildDeck extends AppCompatActivity {

    Card testCard;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_build_screen);

        testCard = Card.getTestCard();

        EditText txtName = (EditText) findViewById(R.id.txtName);
        txtName.setText(testCard.getM_strName());

        EditText txtCost = (EditText) findViewById(R.id.txtCost);
        txtCost.setText(String.valueOf(testCard.getM_intCost()));

        EditText txtQty = (EditText) findViewById(R.id.txtQty);
        txtQty.setText(String.valueOf(1));
        //setContentView(R.layout.activity_build_screen);
    }

    public void btnCancelOnClick (View view)
    {

        finish();
        //Intent intent = new Intent(this, MainScreen.class);
        //intent.putExtra() <-Adds info to be passed into the new activity, such as deck loading.
       // startActivity(intent);
    }

}
