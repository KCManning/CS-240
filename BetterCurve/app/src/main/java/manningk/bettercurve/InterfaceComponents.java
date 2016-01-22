package manningk.bettercurve;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Kevin on 1/21/2016.
 */
public class InterfaceComponents {
    private EditText txtName;
    private EditText txtCost;
    private EditText txtQty;
    private Button btnRaise;
    private Button btnLower;
    private Button btnAddCard;

    public InterfaceComponents() {
    }

    public void makeTestInterface(Context context, Card testCard)
    {
        txtName = new EditText(context);
        txtName.setText(testCard.getM_strName());
        /*
        TextView txtInfo = new TextView(this);
        txtInfo.setText("Hello World Android !");
        txtInfo.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        txtInfo.setTextSize(20);
        txtInfo.setTextColor(Color.GREEN);
         */
        txtCost = new EditText(context);
        txtQty = new EditText(context);
        btnRaise = new Button(context);
        btnLower = new Button(context);
        btnAddCard = new Button(context);

        //mainLayout.addView(txtName);
       // mainLayout.addView(btnAddCard);
    }
}


