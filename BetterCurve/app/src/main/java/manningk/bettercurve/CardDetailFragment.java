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
    static public Card card;

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



        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null){
            DataManager dm = DataManager.getManager(getActivity());

            dm.db.getCard((int) infoID + 1);


            TextView txtCardName = (TextView) view.findViewById(R.id.txtCardName);
            txtCardName.setText(card.getM_strName());

            TextView txtCost = (TextView) view.findViewById(R.id.txtCost);
            txtCost.setText(Integer.toString(card.getM_intCost()));

            TextView txtCardNumber = (TextView) view.findViewById(R.id.txtCardNumber);
            String cardNumber = card.getM_strSet() + card.getM_intSetNumber();
            txtCardNumber.setText(cardNumber);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("demoId", infoID);
    }

    public void setCard(long id) {
        this.infoID = id;
    }


}
