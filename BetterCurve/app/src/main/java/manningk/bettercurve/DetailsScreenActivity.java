package manningk.bettercurve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/26/2016.
 */


public class DetailsScreenActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details_screen);
        String passedName = "Card Details";


        ArrayList <String[]> list = (ArrayList<String[]>) getIntent().getSerializableExtra(passedName);
        //int size = getIntent().getExtras().size();

        //String string1[] = new String[size];
        //string1[] = getIntent().getExtras().getStringArray(passedName);

        int hold = 0;
        //***
        //Program crashing after this, CANNOT find cause
        //Need outside eyes
        //***
    }

    public void btnDetailsCancelOnClick(View view)
    {
       finish();
    }

    public void btnAddOnClick(View view){

        Intent resultIntent = new Intent();
        resultIntent.putExtra(BuildDeckActivity.CARD_ID, "bil125");

        setResult(RESULT_OK, resultIntent);

        finish();
    }

}


