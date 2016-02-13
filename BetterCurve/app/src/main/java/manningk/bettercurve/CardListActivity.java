package manningk.bettercurve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Kevin on 2/11/2016.
 */
public class CardListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);

        //pollDecks();

    }

    public void btnListCancelOnClick(View view)
    {
        finish();
    }

}
