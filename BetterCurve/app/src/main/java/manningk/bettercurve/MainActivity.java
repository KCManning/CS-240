package manningk.bettercurve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //pollDecks();

    }


    public void btnBuildScreenOnClick(View view) {

        Intent intent = new Intent(this, BuildDeckActivity.class);
        //intent.putExtra() <-Adds info to be passed into the new activity, such as deck loading.
        startActivity(intent);
    }

    /*public void btnsCancelOnClick (View view)
    {
        setContentView(R.layout.activity_main_screen);

    }*/

    public void btnsAddCardOnClick(View view) {
        setContentView(R.layout.activity_main_screen);

    }

    public void btnStatsScreenOnClick(View view) {
        Intent intent = new Intent(this, DeckStatsActivity.class);
        //intent.putExtra() <-Adds info to be passed into the new activity, such as deck loading.
        startActivity(intent);

    }

}
