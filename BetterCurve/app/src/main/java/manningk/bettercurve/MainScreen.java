package manningk.bettercurve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }



    public void btnBuildScreenOnClick (View view)
    {

        Intent intent = new Intent(this, BuildDeck.class);
        //intent.putExtra() <-Adds info to be passed into the new activity, such as deck loading.
        startActivity(intent);

    }

    /*public void btnsCancelOnClick (View view)
    {
        setContentView(R.layout.activity_main_screen);

    }*/

    public void btnsAddCardOnClick (View view)
    {
        setContentView(R.layout.activity_main_screen);

    }

    public void btnStatsScreenOnClick (View view)
    {
        Intent intent = new Intent(this, DeckStats.class);
        //intent.putExtra() <-Adds info to be passed into the new activity, such as deck loading.
        startActivity(intent);

    }

}
