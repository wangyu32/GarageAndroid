package garage.wangyu.com.garageandroid.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import garage.wangyu.com.garageandroid.adapter.GarageAdapter;

public class GarageActivity extends AppCompatActivity {

    private GridView garageGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        garageGridView = findViewById(R.id.garage_gv);
        garageGridView.setAdapter(new GarageAdapter(GarageActivity.this));


    }
}
