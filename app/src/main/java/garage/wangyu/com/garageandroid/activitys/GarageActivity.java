package garage.wangyu.com.garageandroid.activitys;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import garage.wangyu.com.garageandroid.adapter.GarageAdapter;
import garage.wangyu.com.garageandroid.entity.GarageItem;

public class GarageActivity extends BaseActivity {

    private GridView gridView;

    private GarageAdapter garageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        gridView = findViewById(R.id.garage_gv);

        try {
            List<GarageItem> list = userService.queryAllGarageItems(userService.getGarageId());
            this.garageAdapter = new GarageAdapter(GarageActivity.this, list, null, true);
            gridView.setAdapter(garageAdapter);
        } catch (Exception e){
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
