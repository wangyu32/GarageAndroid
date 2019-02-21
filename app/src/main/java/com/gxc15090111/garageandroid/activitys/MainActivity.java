package com.gxc15090111.garageandroid.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.gxc15090111.common.Result;

import java.util.List;

import com.gxc15090111.garageandroid.entity.StopRecording;
import com.gxc15090111.garageandroid.entity.User;
import com.gxc15090111.garageandroid.enums.CarStatusEnum;
import com.gxc15090111.garageandroid.enums.UserEnum;
import com.gxc15090111.garageandroid.service.UserService;
import com.gxc15090111.garageandroid.util.NullUtil;

/**
 * 主页：入库码，出库码，扫描入库，扫描出库
 */
public class MainActivity extends BaseActivity {

    private Button mainGarageButton;  //入库二维码按钮
    private Button mainComeinQrButton;  //入库二维码按钮
    private Button mainComeoutQrButton; //出库二维码按钮
    private Button mainComeinScanButton;    //扫描入库按钮
    private Button mainComeoutScanButton;   //扫描出库按钮
    private Button mainStopRecordingButton;      //消费查询
    private Button mainMyGarageItemButton;      //我的车位
    private Button mainBackButton;      //返回按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Integer userTpye = null;

        try {
            login_sp = getSharedPreferences("userInfo", 0);
            String phone = login_sp.getString("USER_NAME", "");
            Result result = userService.getUserByPhone(phone);
            User user = userService.convertJSONObjectToUser((JSONObject) result.getData());
            userTpye = user.getType();
        } catch (Exception e) {
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        mainGarageButton = findViewById(R.id.main_garage_btn);
        mainComeinQrButton = findViewById(R.id.main_comein_qr_btn);
        mainComeoutQrButton = findViewById(R.id.main_comeout_qr_btn);
        mainComeinScanButton = findViewById(R.id.main_comein_scan_btn);
        mainComeoutScanButton = findViewById(R.id.main_comeout_scan_btn);
        mainStopRecordingButton = findViewById(R.id.main_stop_recording_btn);
        mainMyGarageItemButton = findViewById(R.id.main_my_garage_item_btn);
        mainBackButton = findViewById(R.id.main_back_btn);

        setListeners();

        if (UserEnum.ADMIN != UserEnum.getByCode(userTpye)) {
            //不是管理员，隐藏出库，入库二维码按钮
//            mainGarageButton.setVisibility(View.INVISIBLE);//表示隐藏
//            mainComeinQrButton.setVisibility(View.INVISIBLE);
//            mainComeoutQrButton.setVisibility(View.INVISIBLE);
            hiddenButton(mainGarageButton, mainComeinQrButton, mainComeoutQrButton);
        }
    }

    private void hiddenButton(Button... buttons){
        for(Button button : buttons){
            button.setVisibility(View.INVISIBLE);
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) button.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.height = 0;
            linearParams.setMargins(0,0,0,0);
        }
    }

    public void setListeners() {
        OnClick onClick = new OnClick();
        mainGarageButton.setOnClickListener(onClick);
        mainComeinQrButton.setOnClickListener(onClick);
        mainComeoutQrButton.setOnClickListener(onClick);
        mainComeinScanButton.setOnClickListener(onClick);
        mainComeoutScanButton.setOnClickListener(onClick);
        mainStopRecordingButton.setOnClickListener(onClick);
        mainMyGarageItemButton.setOnClickListener(onClick);
        mainBackButton.setOnClickListener(onClick);
    }

    public class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //跳转到
            Intent intent = null;
            switch (v.getId()) {
                case R.id.main_garage_btn:
                    intent = new Intent(MainActivity.this, GarageActivity.class);
                    break;
                case R.id.main_comein_qr_btn:
                    intent = new Intent(MainActivity.this, ComeinQrCodeActivity.class);
                    break;
                case R.id.main_comeout_qr_btn:
                    intent = new Intent(MainActivity.this, ComeoutQrCodeActivity.class);
                    break;
                case R.id.main_comein_scan_btn:
                    intent = new Intent(MainActivity.this, ComeinScanActivity.class);
                    break;
                case R.id.main_comeout_scan_btn:
                    intent = new Intent(MainActivity.this, ComeoutScanActivity.class);
                    break;
                case R.id.main_stop_recording_btn:
                    intent = new Intent(MainActivity.this, StopRecordingActivity.class);
                    break;
                case R.id.main_my_garage_item_btn:
                    boolean isComeIn = isUserComein();
                    if(isComeIn){
                        intent = new Intent(MainActivity.this, MyGarageItemActivity.class);
                    } else {
                        Toast.makeText(MainActivity.this, "您没有停车入库，无法查看停车位", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.main_back_btn:
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    break;
            }

            if(intent != null){
                startActivity(intent);
            }
        }
    }

    private boolean isUserComein() {
        //查看自己的车位分布
        User user = UserService.getInstance().getLoginUser();
        Long userId = user.getId();
        Long garageid = UserService.getInstance().getGarageId();
        try {
            List<StopRecording> stopList =  UserService.getInstance().queryStopRecodingList(userId, garageid, CarStatusEnum.COME_IN.getValue());
            if(NullUtil.notNull(stopList)){
                return true;
            }
        } catch (Exception e){
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.layout.activity_login:
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        ActionBar actionBar = this.getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//    }

}
