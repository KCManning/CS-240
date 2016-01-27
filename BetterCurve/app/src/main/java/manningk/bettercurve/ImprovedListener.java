package manningk.bettercurve;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Kevin on 1/26/2016.
 */
public class ImprovedListener implements View.OnClickListener
{
    EditText txtQty;
    LinearLayout grpLayoutView;
    Card card;
    public ImprovedListener(EditText txtQty) {
        this.txtQty = txtQty;
    }

    public ImprovedListener(EditText txtQty, LinearLayout grpLayoutView) {
        this.txtQty = txtQty;
        this.grpLayoutView = grpLayoutView;
    }

    public ImprovedListener(Card card) {
        this.card = card;
    }

    @Override
    public void onClick(View v)
    {
        //read your lovely variable
    }

};
