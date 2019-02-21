package com.gxc15090111.garageandroid.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import com.gxc15090111.garageandroid.adapter.GarageAdapter;
import com.gxc15090111.garageandroid.entity.GarageItem;
import com.gxc15090111.garageandroid.entity.StopRecording;
import com.gxc15090111.garageandroid.entity.User;
import com.gxc15090111.garageandroid.enums.CarStatusEnum;
import com.gxc15090111.garageandroid.service.UserService;
import com.gxc15090111.garageandroid.util.NullUtil;

public class MyGarageItemActivity extends BaseActivity {

    private GridView gridView;

    private GarageAdapter garageActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garage_item);

        gridView = findViewById(R.id.my_garage_item_gv);

        try {
            //查看自己的车位分布
            User user = UserService.getInstance().getLoginUser();
            Long userId = user.getId();
            Long garageid = UserService.getInstance().getGarageId();
            List<StopRecording> stopList =  UserService.getInstance().queryStopRecodingList(userId, garageid, CarStatusEnum.COME_IN.getValue());
            StopRecording stopRecording = null;
            if(NullUtil.notNull(stopList)){
                stopRecording = stopList.get(0);
            }

            List<GarageItem> list = userService.queryAllGarageItems(userService.getGarageId());
            this.garageActivity = new GarageAdapter(MyGarageItemActivity.this, list, stopRecording, false);

            gridView.setAdapter(garageActivity);
        } catch (Exception e){
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
}