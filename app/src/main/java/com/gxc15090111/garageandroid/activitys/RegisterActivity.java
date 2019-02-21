package com.gxc15090111.garageandroid.activitys;

import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gxc15090111.common.Result;

import com.gxc15090111.garageandroid.dto.UserRegisterDTO;
import com.gxc15090111.garageandroid.enums.SexEnum;

/**
 * 用户注册
 */
public class RegisterActivity extends BaseActivity {

    private EditText phoneEditText;                    //用户名编辑
    private EditText nameEditText;                     //姓名编辑
    private RadioGroup sexRadioGroup;                  //性别
    private EditText passwordEditText;                //密码编辑
    private EditText passwordConfirmEditText;        //确认密码编辑
    private Button sureButton;                         //确定按钮
    private Button cancelButton;                      //取消按钮

    private Integer sex = 0;//性别0-男;1-女

    //不同按钮按下的监听事件选择
    private View.OnClickListener m_register_Listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_btn_sure:                       //确认按钮的监听事件
                    register_check();
                    break;
                case R.id.register_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class) ;
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    private void setSet(RadioButton radioButton){
        this.sex = SexEnum.getByName(radioButton.getText().toString()).getValue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        phoneEditText = findViewById(R.id.register_edit_phone);
        nameEditText = findViewById(R.id.register_edit_name);
        sexRadioGroup = findViewById(R.id.register_edit_sex);
        passwordEditText = findViewById(R.id.register_edit_password);
        passwordConfirmEditText = findViewById(R.id.register_edit_password_confirm);
        sureButton = findViewById(R.id.register_btn_sure);
        cancelButton = findViewById(R.id.register_btn_cancel);

        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                setSet(radioButton);
            }
        });

        //注册界面两个按钮的监听事件
        sureButton.setOnClickListener(m_register_Listener);
        cancelButton.setOnClickListener(m_register_Listener);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *  确认按钮的监听事件
     */
    public void register_check() {
        String phone = this.phoneEditText.getText().toString();
        String name = this.nameEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();
        String passwordConfirm = this.passwordConfirmEditText.getText().toString();

        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setPhone(phone);
        dto.setName(name);
        dto.setSex(this.sex);
        dto.setPassword(password);
        dto.setPasswordConfirm(passwordConfirm);

        try {
            Result result = userService.register(dto);
            if(result.isSuccess()){
                Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent_Register_to_Login = new Intent(RegisterActivity.this,LoginActivity.class) ;    //切换User Activity至Login Activity
                        startActivity(intent_Register_to_Login);
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
