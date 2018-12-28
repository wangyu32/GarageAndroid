package garage.wangyu.com.garageandroid.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.activity.CaptureActivity;
import com.wangyu.common.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import garage.wangyu.com.garageandroid.constants.Constant;
import garage.wangyu.com.garageandroid.dto.UserComeInDTO;
import garage.wangyu.com.garageandroid.entity.GarageItem;
import garage.wangyu.com.garageandroid.entity.PriceUnit;
import garage.wangyu.com.garageandroid.entity.StopRecording;
import garage.wangyu.com.garageandroid.entity.User;
import garage.wangyu.com.garageandroid.result.ComeinoutResult;
import garage.wangyu.com.garageandroid.util.HttpUtils;
import garage.wangyu.com.garageandroid.util.NullUtil;
import garage.wangyu.com.garageandroid.vo.ComeinoutVO;

/**
 * 入库扫描
 */
public class ComeinScanActivity extends BaseActivity implements View.OnClickListener{

    private Button scanButton; // 扫码
    private TextView scanResultTextView; // 结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comein_scan);

        initView();

        login_sp = getSharedPreferences("userInfo", 0);
        if(login_sp == null || NullUtil.isNull(login_sp.getString("USER_NAME", ""))){
            Toast.makeText(this, "请先登录系统", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ComeinScanActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }

        if(isDevelop()){
            //开发模式直接扫描
            String url = userService.getComeinQrCodeUrl();
            scanComeIn(url);
        }
    }

    private void initView() {
        scanButton = findViewById(R.id.comein_scan_btn);
        scanResultTextView = findViewById(R.id.comein_scan_result);

        scanButton.setOnClickListener(ComeinScanActivity.this);
    }

    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(ComeinScanActivity.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(ComeinScanActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comein_scan_btn:
                startQrCode();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String url = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);

            scanComeIn(url);
        }
    }

    //扫描入库
    private void scanComeIn(String url){
        if(!url.equals(userService.getComeinQrCodeUrl())){
            scanResultTextView.setText("入库二维码错误");
            return;
        }

        String phone = login_sp.getString("USER_NAME", "");
        try {
            Result result = userService.getUserByPhone(phone);
            User user = userService.convertJSONObjectToUser((JSONObject)result.getData());
            UserComeInDTO dto = new UserComeInDTO();
            dto.setGarageId(userService.getGarageId());
            dto.setUserId(user.getId());

            String json = HttpUtils.postJson(url, JSON.toJSONString(dto));
            ComeinoutResult comeinoutResult = JSON.parseObject(json, ComeinoutResult.class);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if(comeinoutResult.isSuccess()){
                //将扫描出的信息显示出来
                ComeinoutVO comeinoutVO = comeinoutResult.getData();
                StopRecording stopRedcording = comeinoutVO.getStopRecording();
                GarageItem garageItem = comeinoutVO.getGarageItem();
                PriceUnit priceUnit = comeinoutVO.getPriceUnit();
                StringBuffer sb = new StringBuffer();
                sb.append("入库状态：").append("入库成功").append("\n");
                sb.append("用户姓名：").append(user.getName()).append("\n");
                sb.append("手机号码：").append(user.getPhone()).append("\n");
                sb.append("入库时间：").append(formatter.format(stopRedcording.getIntime())).append("\n");
                sb.append("停车位置：").append(garageItem.getCode()).append("(").append(garageItem.getLevel()).append("层)").append("\n");
                sb.append("计费方式：").append(priceUnit.getUname()).append("\n");

                String stopRedcordingView = sb.toString();
                scanResultTextView.setText(stopRedcordingView);
            } else {
                StringBuffer sb = new StringBuffer();
                sb.append("入库状态：").append("入库失败：").append(result.getMessage()).append("\n");
                sb.append("用户姓名：").append(user.getName()).append("\n");
                sb.append("手机号码：").append(user.getPhone()).append("\n");
                scanResultTextView.setText(sb.toString());
            }
        } catch (Exception e){
            Toast.makeText(this, "系统错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(ComeinScanActivity.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
