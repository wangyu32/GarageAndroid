package garage.wangyu.com.garageandroid.activitys;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.wangyu.common.Result;

import java.util.List;

import garage.wangyu.com.garageandroid.adapter.StopRecordingAdapter;
import garage.wangyu.com.garageandroid.entity.StopRecording;
import garage.wangyu.com.garageandroid.entity.User;
import garage.wangyu.com.garageandroid.enums.CarStatusEnum;
import garage.wangyu.com.garageandroid.parameter.StopRecordingQueryParameter;

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
