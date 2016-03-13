package manningk.bettercurve;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 3/12/2016.
 */
public class CardListFragment extends ListFragment {

    static interface CardListListener {
        void itemClicked(long id);
    }



    private CardListListener listener;

    private ListView lv;
    DataManager dm;
    static public List<String> cardList;
    public CardListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*
        String[] names = new String[FragmentOne.FragmentOne.length];
        for (int i = 0; i < names.length; i++){
            names[i] = FragmentOne.FragmentOne[i].getName();
        }
        */
        //lv = (ListView) container.findViewById(R.id.lstCards);

        cardList = new ArrayList<String>();

        //lv = (ListView) findViewById(R.id.lstCards);

        dm = DataManager.getManager(getActivity());
        dm.db.getAllCards(CardListFragment.cardList);

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                cardList);
        setListAdapter(arrayAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
        //return inflater.inflate(R.layout.fragment_cards_list, container, false);

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.listener = (CardListListener) context;
    }

    @Override
    public void onListItemClick(ListView listview, View view, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }
}
