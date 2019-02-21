package com.gxc15090111.garageandroid.activitys;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.gxc15090111.common.Result;

import java.util.List;

import com.gxc15090111.garageandroid.adapter.StopRecordingAdapter;
import com.gxc15090111.garageandroid.entity.StopRecording;
import com.gxc15090111.garageandroid.entity.User;
import com.gxc15090111.garageandroid.enums.CarStatusEnum;
import com.gxc15090111.garageandroid.parameter.StopRecordingQueryParameter;

public class StopRecordingActivity extends BaseActivity {

    private ListView listView;
//    private HorizontalScrollView listView;

    private StopRecordingAdapter stopRecordingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoprecording_list_view);

        listView = findViewById(R.id.stoprecording_listview);
        login_sp = getSharedPreferences("userInfo", 0);
        String phone = login_sp.getString("USER_NAME", "");
        try {
            Result result = userService.getUserByPhone(phone);
            User user = userService.convertJSONObjectToUser((JSONObject)result.getData());
            StopRecordingQueryParameter parameter = new StopRecordingQueryParameter();
            parameter.setGarageid(userService.getGarageId());
            parameter.setUserid(user.getId());
            parameter.setStatus(CarStatusEnum.COME_OUT.getValue());
            List<StopRecording> list = userService.queryStopRecodingList(parameter);
            this.stopRecordingAdapter = new StopRecordingAdapter(StopRecordingActivity.this, list);
            listView.setAdapter(stopRecordingAdapter);
        } catch (Exception e){
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
