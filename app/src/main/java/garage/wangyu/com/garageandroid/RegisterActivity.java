package garage.wangyu.com.garageandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wangyu.common.Result;

import java.util.Properties;

import garage.wangyu.com.garageandroid.dto.UserRegisterDTO;
import garage.wangyu.com.garageandroid.enums.SexEnum;
import garage.wangyu.com.garageandroid.util.HttpUtils;
import garage.wangyu.com.garageandroid.util.PropertiesUtils;
//import garage.wangyu.com.garageandroid.util.HttpRequestUtil;

/**
 * 用户注册
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText phoneEditText;                    //用户名编辑
    private EditText nameEditText;                     //姓名编辑
    private RadioGroup sexRadioGroup;                  //性别
    private EditText passwordEditText;                //密码编辑
    private EditText passwordConfirmEditText;        //确认密码编辑
    private Button sureButton;                         //确定按钮
    private Button cancelButton;                      //取消按钮
    private UserDataManager mUserDataManager;         //用户数据管理类

    private Integer sex = 0;//性别0-男;1-女

    //不同按钮按下的监听事件选择
    private View.OnClickListener m_register_Listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_btn_sure:                       //确认按钮的监听事件
                    register_check();
                    break;
                case R.id.register_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                    Intent intent_Register_to_Login = new Intent(RegisterActivity.this,LoginActivity.class) ;    //切换User Activity至Login Activity
                    startActivity(intent_Register_to_Login);
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

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
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
            String url = PropertiesUtils.getProperties("serviceUrl") + "/user/register";
            String json = HttpUtils.postJson(url, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);

            if(result.isSuccess()){
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//            String userName = phoneEditText.getText().toString().trim();
//            String userPwd = nameEditText.getText().toString().trim();
//            String userPwdCheck = null; //sexRadioGroup.getText().toString().trim();
//            //检查用户是否存在
//            int count=mUserDataManager.findUserByName(userName);
//            //用户已经存在时返回，给出提示文字
//            if(count>0){
//                Toast.makeText(this, getString(R.string.name_already_exist, userName),Toast.LENGTH_SHORT).show();
//                return ;
//            }
//            if(userPwd.equals(userPwdCheck)==false){     //两次密码输入不一样
//                Toast.makeText(this, getString(R.string.pwd_not_the_same),Toast.LENGTH_SHORT).show();
//                return ;
//            } else {
//                UserData mUser = new UserData(userName, userPwd);
//                mUserDataManager.openDataBase();
//                long flag = mUserDataManager.insertUserData(mUser); //新建用户信息
//                if (flag == -1) {
//                    Toast.makeText(this, getString(R.string.register_fail),Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(this, getString(R.string.register_success),Toast.LENGTH_SHORT).show();
//                    Intent intent_Register_to_Login = new Intent(RegisterActivity.this,LoginActivity.class) ;    //切换User Activity至Login Activity
//                    startActivity(intent_Register_to_Login);
//                    finish();
//                }
//            }
    }
}
