package garage.wangyu.com.garageandroid.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangyu.common.Result;

import garage.wangyu.com.garageandroid.dto.UserLoginDTO;

public class LoginActivity extends BaseActivity {                 //登录界面活动

    private EditText phoneEditText;                    //用户名编辑
    private EditText passwordEditText;                //密码编辑
    private Button mRegisterButton;                   //注册按钮
    private Button mLoginButton;                      //登录按钮

    private TextView mChangepwdText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //初始化
        userService.init();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //通过id找到相应的控件
        phoneEditText = (EditText) findViewById(R.id.login_edit_phone);
        passwordEditText = (EditText) findViewById(R.id.login_edit_password);
        mRegisterButton = (Button) findViewById(R.id.login_btn_register);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);

        mChangepwdText = (TextView) findViewById(R.id.login_text_change_pwd);

        login_sp = getSharedPreferences("userInfo", 0);
        String phone = login_sp.getString("USER_NAME", "");
        String password = login_sp.getString("PASSWORD", "");
        UserLoginDTO dto = new UserLoginDTO();
        dto.setPhone(phone);
        dto.setPassword(password);
        try {
            //如果有登录信息了，直接跳到主页
            Result result = userService.login(dto);
            if (result.isSuccess()) {
                phoneEditText.setText(phone);
                passwordEditText.setText(password);
            } else {
                phoneEditText.setText(phone);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
        }

        mRegisterButton.setOnClickListener(mListener);                      //采用OnClickListener方法设置不同按钮按下之后的监听事件
        mLoginButton.setOnClickListener(mListener);
        mChangepwdText.setOnClickListener(mListener);

        ImageView image = (ImageView) findViewById(R.id.logo);             //使用ImageView显示logo
        image.setImageResource(R.drawable.logo);
    }

    OnClickListener mListener = new OnClickListener() {                  //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn_register:                            //登录界面的注册按钮
                    Intent intent_Login_to_Register = new Intent(LoginActivity.this, RegisterActivity.class);    //切换Login Activity至User Activity
                    startActivity(intent_Login_to_Register);
                    finish();
                    break;
                case R.id.login_btn_login:                              //登录界面的登录按钮
                    login();
                    break;
                case R.id.login_text_change_pwd:                             //登录界面的注销按钮
                    Intent intent_Login_to_reset = new Intent(LoginActivity.this, ResetpwdActivity.class);    //切换Login Activity至User Activity
                    startActivity(intent_Login_to_reset);
                    finish();
                    break;
            }
        }
    };

    //登录按钮监听事件
    public void login() {
        String phone = this.phoneEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();

        UserLoginDTO dto = new UserLoginDTO();
        dto.setPhone(phone);
        dto.setPassword(password);

        try {
            Result result = userService.login(dto);
            if (result.isSuccess()) {
                //保存用户名和密码
                SharedPreferences.Editor editor = login_sp.edit();
                editor.putString("USER_NAME", phone);
                editor.putString("PASSWORD", password);
                editor.commit();

                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            } else {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
        }
    }
}
