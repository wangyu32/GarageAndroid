package garage.wangyu.com.garageandroid.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangyu.common.Result;

import garage.wangyu.com.garageandroid.UserDataManager;
import garage.wangyu.com.garageandroid.dto.UserLoginDTO;

public class LoginActivity extends BaseActivity {                 //登录界面活动

    private EditText phoneEditText;                    //用户名编辑
    private EditText passwordEditText;                //密码编辑
    private Button mRegisterButton;                   //注册按钮
    private Button mLoginButton;                      //登录按钮
    private CheckBox mRememberCheck;

    private SharedPreferences login_sp;
    private String userNameValue, passwordValue;

    private View loginView;                           //登录
    private View loginSuccessView;
    private TextView loginSuccessShow;
    private TextView mChangepwdText;
    private UserDataManager mUserDataManager;         //用户数据管理类

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //通过id找到相应的控件
        phoneEditText = (EditText) findViewById(R.id.login_edit_phone);
        passwordEditText = (EditText) findViewById(R.id.login_edit_password);
        mRegisterButton = (Button) findViewById(R.id.login_btn_register);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);
//        mCancleButton = (Button) findViewById(R.id.login_btn_cancle);

        loginView = findViewById(R.id.login_view);
        loginSuccessView = findViewById(R.id.login_success_view);
        loginSuccessShow = (TextView) findViewById(R.id.login_success_show);

        mChangepwdText = (TextView) findViewById(R.id.login_text_change_pwd);
//        mRememberCheck = (CheckBox) findViewById(R.id.Login_Remember);

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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
        }

//        boolean choseRemember = login_sp.getBoolean("mRememberCheck", false);
//        boolean choseAutoLogin = login_sp.getBoolean("mAutologinCheck", false);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
//        if (choseRemember) {
//            phoneEditText.setText(phone);
//            passwordEditText.setText(password);
//            mRememberCheck.setChecked(true);
//        }

        mRegisterButton.setOnClickListener(mListener);                      //采用OnClickListener方法设置不同按钮按下之后的监听事件
        mLoginButton.setOnClickListener(mListener);
//        mCancleButton.setOnClickListener(mListener);
        mChangepwdText.setOnClickListener(mListener);

        ImageView image = (ImageView) findViewById(R.id.logo);             //使用ImageView显示logo
        image.setImageResource(R.drawable.logo);

//        if (mUserDataManager == null) {
//            mUserDataManager = new UserDataManager(this);
//            mUserDataManager.openDataBase();                              //建立本地数据库
//        }
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
//                case R.id.login_btn_cancle:                             //登录界面的注销按钮
//                    cancel();
//                    break;
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


//        if (isUserNameAndPwdValid()) {
//        SharedPreferences.Editor editor = login_sp.edit();
//            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
//            if(result==1){                                             //返回1说明用户名和密码均正确
//                //保存用户名和密码
//                editor.putString("USER_NAME", userName);
//                editor.putString("PASSWORD", userPwd);
//
//                //是否记住密码
//                if(mRememberCheck.isChecked()){
//                    editor.putBoolean("mRememberCheck", true);
//                }else{
//                    editor.putBoolean("mRememberCheck", false);
//                }
//                editor.commit();
//
//                Intent intent = new Intent(LoginActivity.this,UserActivity.class) ;    //切换Login Activity至User Activity
//                startActivity(intent);
//                finish();
//                Toast.makeText(this, getString(R.string.login_success),Toast.LENGTH_SHORT).show();//登录成功提示
//            }else if(result==0){
//                Toast.makeText(this, getString(R.string.login_fail),Toast.LENGTH_SHORT).show();  //登录失败提示
//            }
//        }
    }


    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }

}
