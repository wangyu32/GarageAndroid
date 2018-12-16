package garage.wangyu.com.garageandroid.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * 主页：入库码，出库码，扫描入库，扫描出库
 */
public class MainActivity extends BaseActivity {

    private Button mainComeinQrButton;  //入库二维码按钮
    private Button mainComeoutQrButton; //出库二维码按钮
    private Button mainComeinScanButton;    //扫描入库按钮
    private Button mainComeoutScanButton;   //扫描出库按钮
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

        mainComeinQrButton = findViewById(R.id.main_comein_qr_btn);
        mainComeoutQrButton = findViewById(R.id.main_comeout_qr_btn);
        mainComeinScanButton = findViewById(R.id.main_comein_scan_btn);
        mainComeoutScanButton = findViewById(R.id.main_comeout_scan_btn);
        mainBackButton = findViewById(R.id.main_back_btn);

        setListeners();
    }

    public void setListeners(){
        OnClick onClick = new OnClick();
        mainComeinQrButton.setOnClickListener(onClick);
        mainComeoutQrButton.setOnClickListener(onClick);
        mainComeinScanButton.setOnClickListener(onClick);
        mainComeoutScanButton.setOnClickListener(onClick);
        mainBackButton.setOnClickListener(onClick);
    }

    public class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //跳转到
            Intent intent = null;
            switch (v.getId()){
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
