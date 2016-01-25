package manningk.bettercurve;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Kevin on 1/24/2016.
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

    /*
    Code from: http://www.itcsolutions.eu/2011/08/27/android-tutorial-4-procedural-vs-declarative-design-of-user-interfaces/
    package eu.itcsolutions.tutorial.android;

import android.app.Activity;
import android.os.Bundle;

public class HelloActivity extends Activity {
    /** Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //call to the base class method
        super.onCreate(savedInstanceState);
        //set the layout of the display in a declarative manner
        setContentView(R.layout.main);
    }
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//call the base class method
        super.onCreate(savedInstanceState);

        //set the title bar text
        this.setTitle("First Android Application");
    }

            //define and create a linear layout
        LinearLayout mainLayout;
        mainLayout = new LinearLayout(this);
        //set vertical orientation
	mainLayout.setOrientation(1);

	        TextView txtInfo = new TextView(this);
        txtInfo.setText("Hello World Android !");
        txtInfo.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        txtInfo.setTextSize(20);
        txtInfo.setTextColor(Color.GREEN);
        //txtInfo.setTextColor(Color.rgb(0, 255, 0));

               Button btnClick = new Button(this);
        //define the button caption
        btnClick.setText("Click me !");

               mainLayout.addView(txtInfo);
        mainLayout.addView(btnAbout);

                //attach the layout to the Activity display
        this.setContentView(mainLayout);

        mainLayout.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);

        //define the LayoutParams for the TextView
//width - equal to the content
//height - equal to the content
android.widget.LinearLayout.LayoutParams txtLayoutParams =
    new LinearLayout.LayoutParams(
        android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
//set the LayoutParams
txtInfo.setLayoutParams(txtLayoutParams);

//define the LayoutParams for the Button
//width - 120 px
//height - equal to the content
android.widget.LinearLayout.LayoutParams btnLayoutParams =
     new LinearLayout.LayoutParams(
         120,
         android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
//set the LayoutParams
btnClick.setLayoutParams(btnLayoutParams);

     */
}


