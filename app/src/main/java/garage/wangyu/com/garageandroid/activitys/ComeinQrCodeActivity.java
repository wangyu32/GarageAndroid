package garage.wangyu.com.garageandroid.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import garage.wangyu.com.garageandroid.util.NullUtil;
import garage.wangyu.com.garageandroid.util.QRCodeUtil;

/**
 * 入库二维码
 */
public class ComeinQrCodeActivity extends BaseActivity {

    private ImageView imageView;

    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comein_qr_code);

        imageView = findViewById(R.id.comein_rq_code_image);
        backButton = findViewById(R.id.comein_rq_code_back_btn);
        backButton.setOnClickListener(new OnClick());

        login_sp = getSharedPreferences("userInfo", 0);
        if(login_sp == null || NullUtil.isNull(login_sp.getString("USER_NAME", ""))){
            Toast.makeText(this, "请先登录系统", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ComeinQrCodeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }

        int width = 300;
        Bitmap bitmap = QRCodeUtil.createQRCodeBitmap(userService.getComeinQrCode(), width, width);
        imageView.setImageBitmap(bitmap);
    }

    public class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //跳转到
            Intent intent = null;
            switch (v.getId()){
                case R.id.comein_rq_code_back_btn:
                    intent = new Intent(ComeinQrCodeActivity.this, MainActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
