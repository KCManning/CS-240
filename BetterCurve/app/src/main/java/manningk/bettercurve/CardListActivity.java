package manningk.bettercurve;

import android.app.FragmentTransaction;
import android.content.Intent;
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
public class CardListActivity extends FragmentActivity implements CardListFragment.CardListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_screen);
        getSupportFragmentManager().beginTransaction().replace(R.id.detailsFragment, new CardListFragment()).addToBackStack(null).commit();
        //getFragmentManager().beginTransaction().addToBackStack(null).commit();
    }


    @Override
    public void itemClicked(long id) {
        CardDetailFragment details = new CardDetailFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.detailsFragment, details);
        details.setCard(id);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }

    //private DataManager dm;

    //@Override
    // protected void onCreate(Bundle savedInstanceState) {
    //   super.onCreate(savedInstanceState);
    //  setContentView(R.layout.activity_card_list_screen);
    //  dm = DataManager.getManager(this);


    //lv = (ListView) findViewById(R.id.lstCards);

    // Instansiating an array list (you don't need to do this,
    // you already have yours).
    //List<String> cardList = new ArrayList<String>();

    //dm.db.getAllCards(cardList);
//        dm = DataManager.getManager(getActivity());

    // This is the array adapter, it takes the context of the activity as a
    // first parameter, the type of list view as a second parameter and your
    // array as a third parameter.
    //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
    //this,
    //android.R.layout.simple_list_item_1,
    //cardList);

    //lv.setAdapter(arrayAdapter);


    public void btnListCancelOnClick(View view) {
        finish();
    }
    public void btnAddCardOnClick(View view){

        Intent intent = this.getIntent();
        intent.putExtra("CardID", Integer.toString(CardDetailFragment.card.getM_ID()));
        this.setResult(RESULT_OK, intent);
        finish();
    }

}
