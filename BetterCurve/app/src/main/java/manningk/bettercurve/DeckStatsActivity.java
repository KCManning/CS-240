package manningk.bettercurve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Kevin on 1/19/2016.
 */
public class DeckStatsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_screen);
    }

    public void btnGoHomeOnClick (View view)
    {
        finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra() <-Adds info to be passed into the new activity, such as deck loading.
        //startActivity(intent);
    }


}