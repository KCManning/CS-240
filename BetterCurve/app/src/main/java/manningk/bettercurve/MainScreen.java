package manningk.bettercurve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    public void btnStatsScreenOnClick (View view)
    {
        setContentView(R.layout.activity_stats_screen);

    }

    public void btnBuildScreenOnClick (View view)
    {
        setContentView(R.layout.activity_build_screen);

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
