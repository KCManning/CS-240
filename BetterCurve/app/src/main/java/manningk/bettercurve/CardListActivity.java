package manningk.bettercurve;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2/11/2016.
 */
public class CardListActivity extends FragmentActivity implements DetailsScreenActivity. {
    private ListView lv;
    private DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_screen);
        dm = DataManager.getManager(this);

        getFragmentManager().beginTransaction().add(R.id.a, new DetailsScreenActivity()).commit();
        getFragmentManager().beginTransaction().add(R.id.fragment_container, new AddCardActivity()).commit();


        lv = (ListView) findViewById(R.id.lstCards);

        // Instansiating an array list (you don't need to do this,
        // you already have yours).
        List<String> cardList = new ArrayList<String>();

        dm.db.getAllCards(cardList);


        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                cardList );

        lv.setAdapter(arrayAdapter);


    }

    public void btnListCancelOnClick(View view) {
        finish();
    }

}
