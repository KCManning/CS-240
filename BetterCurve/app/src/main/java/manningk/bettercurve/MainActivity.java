package manningk.bettercurve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import java.io.BufferedInputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    DataManager dm;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        dm = DataManager.getManager(this);
        ListView lstDecks = (ListView) findViewById(R.id.lstDecks);
        //adapter= new ArrayAdapter<String>(this, android.R.id.lstDecks,listItems);

        //pollDecks();
    }

    public void fillList() {
        int j = 0;
        for (int i = dm.getDeckCount(); i > 0; i--) {
            adapter.add(dm.getDeck(j).getDeckName() + dm.getDeck(j).getDeckType() +
                    dm.getDeck(j).getGameName());
            j++;
        }
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
