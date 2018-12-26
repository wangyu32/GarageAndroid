package garage.wangyu.com.garageandroid.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.wangyu.common.Result;

import garage.wangyu.com.garageandroid.entity.User;
import garage.wangyu.com.garageandroid.enums.UserEnum;

/**
 * 主页：入库码，出库码，扫描入库，扫描出库
 */
public class MainActivity extends BaseActivity {

    private Button mainGarageButton;  //入库二维码按钮
    private Button mainComeinQrButton;  //入库二维码按钮
    private Button mainComeoutQrButton; //出库二维码按钮
    private Button mainComeinScanButton;    //扫描入库按钮
    private Button mainComeoutScanButton;   //扫描出库按钮
    private Button mainBackButton;      //返回按钮
    private Button mainStopRecordingButton;      //返回按钮

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
        mainBackButton = findViewById(R.id.main_back_btn);
        mainStopRecordingButton = findViewById(R.id.main_stop_recording_btn);

        setListeners();

        if (UserEnum.ADMIN != UserEnum.getByCode(userTpye)) {
            //不是管理员，隐藏出库，入库二维码按钮
            mainComeinQrButton.setVisibility(View.INVISIBLE);//表示隐藏
            mainComeoutQrButton.setVisibility(View.INVISIBLE);//表示隐藏
        }
    }

    public void setListeners() {
        OnClick onClick = new OnClick();
        mainGarageButton.setOnClickListener(onClick);
        mainComeinQrButton.setOnClickListener(onClick);
        mainComeoutQrButton.setOnClickListener(onClick);
        mainComeinScanButton.setOnClickListener(onClick);
        mainComeoutScanButton.setOnClickListener(onClick);
        mainBackButton.setOnClickListener(onClick);
        mainStopRecordingButton.setOnClickListener(onClick);
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
                case R.id.main_back_btn:
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    break;
                case R.id.main_stop_recording_btn:
                    intent = new Intent(MainActivity.this, StopRecordingActivity.class);
                    break;
            }
            startActivity(intent);
        }
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
