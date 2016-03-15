package manningk.bettercurve;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 3/12/2016.
 */
public class CardListFragment extends android.support.v4.app.ListFragment
        implements OnItemClickListener {

    interface CardListListener {
        void itemClicked(long id);
    }


    private CardListListener listener;

    private ListView lv;
    DataManager dm;
    static public List<String> cardList;

    public CardListFragment() {
        // Required empty public constructor
    }

    /*
        public void onStart()
        {
            // Inflate the layout for this fragment

            View v = getListView();
            lv = (ListView)getView().findViewById(R.id.list);
            //lv = getListView();


            cardList = new ArrayList<String>();

            //lv = (ListView) getView().findViewById(R.id.lstCards);

            dm = DataManager.getManager(getActivity());
            dm.db.getAllCards(CardListFragment.cardList);

            // This is the array adapter, it takes the context of the activity as a
            // first parameter, the type of list view as a second parameter and your
            // array as a third parameter.
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    cardList);
            lv.setAdapter(arrayAdapter);
        }
    */
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cards_list, container);
        //lv.setAdapter(arrayAdapter);
        //RelativeLayout fragList = new RelativeLayout(getContext());
        //fragList.addView(lv);
        return super.onCreateView(inflater, container, savedInstanceState);
        //return v;

    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cardList = new ArrayList<String>();

        //lv = (ListView) getListView().findViewById(R.id.list);

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
        getListView().setOnItemClickListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.listener = (CardListListener) context;
    }

    //@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }
}
