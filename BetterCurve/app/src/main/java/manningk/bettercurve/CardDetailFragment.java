package manningk.bettercurve;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardDetailFragment extends Fragment {

    private long infoID;
    private Card card;

    public CardDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (savedInstanceState != null) {
            infoID = savedInstanceState.getLong("demoId");
        }



        return inflater.inflate(R.layout.fragment_cards, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();



    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("demoId", infoID);
    }

    public void setCard(long id) {
        this.infoID = id;


    }


}
