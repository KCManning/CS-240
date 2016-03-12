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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataManager dm;
    private ListView lstDecks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        dm = DataManager.getManager(this);
        ListView lstDecks = (ListView) findViewById(R.id.lstDecks);

        //lv = (ListView) findViewById(R.id.lstCards);

        // Instansiating an array list (you don't need to do this,
        // you already have yours).
        List<String> deckList = new ArrayList<String>();
        fillList(deckList);


        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                deckList);

        lstDecks.setAdapter(arrayAdapter);
        //adapter= new ArrayAdapter<String>(this, android.R.id.lstDecks,listItems);

        //pollDecks();
    }

    public void fillList(List<String> deckList) {
        int j = 0;
        for (int i = dm.getDeckCount(); i > 0; i--) {
            deckList.add(dm.getDeck(j).getDeckName() + dm.getDeck(j).getDeckType() +
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
