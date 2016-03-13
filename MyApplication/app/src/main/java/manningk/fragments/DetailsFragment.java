package manningk.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kevin on 3/13/2016.
 */
public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
                             Bundle savedInstanceState) {

        //return super.onCreateView(inflater, containter, savedInstanceState);
        return inflater.inflate(R.layout.activity_card_list, containter, false);
    }
}