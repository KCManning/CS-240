package manningk.bettercurve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity {
    Card testCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }



    public void btnBuildScreenOnClick (View view)
    {
        setContentView(R.layout.activity_build_screen);

        testCard = Card.getTestCard();

        EditText txtName = (EditText) findViewById(R.id.txtName);
        txtName.setText(testCard.getM_strName());

        EditText txtCost = (EditText) findViewById(R.id.txtCost);
        txtCost.setText(String.valueOf(testCard.getM_intCost()));

        EditText txtQty = (EditText) findViewById(R.id.txtQty);
        txtQty.setText(String.valueOf(1));

    }

    public void btnsCancelOnClick (View view)
    {
        setContentView(R.layout.activity_main_screen);

    }

    public void btnsAddCardOnClick (View view)
    {
        setContentView(R.layout.activity_main_screen);

    }

}
