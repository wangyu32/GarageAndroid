package garage.wangyu.com.garageandroid.activitys;

import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wangyu.common.Result;

import garage.wangyu.com.garageandroid.dto.UserChangePasswordDTO;
import garage.wangyu.com.garageandroid.dto.UserLoginDTO;

/**
 * 修改密码
 */
public class ResetpwdActivity extends BaseActivity {

    private EditText phoneEditText;                    //用户名编辑
    private EditText passwordOldEditText;            //旧密码编辑
    private EditText passwordNewEditText;            //新密码编辑
    private EditText passwordNewConfirmEditText;    //确认新密码编辑

    private Button mSureButton;                       //确定按钮
    private Button mCancelButton;                     //取消按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpwd);

        phoneEditText = (EditText) findViewById(R.id.resetpwd_edit_phone);
        passwordOldEditText = (EditText) findViewById(R.id.resetpwd_edit_password_old);
        passwordNewEditText = (EditText) findViewById(R.id.resetpwd_edit_password_new);
        passwordNewConfirmEditText = (EditText) findViewById(R.id.resetpwd_edit_password_new_confirm);

        mSureButton = (Button) findViewById(R.id.resetpwd_btn_sure);
        mCancelButton = (Button) findViewById(R.id.resetpwd_btn_cancel);

        mSureButton.setOnClickListener(m_resetpwd_Listener);      //注册界面两个按钮的监听事件
        mCancelButton.setOnClickListener(m_resetpwd_Listener);

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
                passwordOldEditText.setText(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
        }
    }

    View.OnClickListener m_resetpwd_Listener = new View.OnClickListener() {    //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.resetpwd_btn_sure:                       //确认按钮的监听事件
                    resetpwd_check();
                    break;
                case R.id.resetpwd_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                    Intent intent_Resetpwd_to_Login = new Intent(ResetpwdActivity.this,LoginActivity.class) ;    //切换Resetpwd Activity至Login Activity
                    startActivity(intent_Resetpwd_to_Login);
                    finish();
                    break;
            }
        }
    };

    public void resetpwd_check() {                                //确认按钮的监听事件
        String phone = phoneEditText.getText().toString().trim();
        String passworOld = passwordOldEditText.getText().toString().trim();
        String passworNew = passwordNewEditText.getText().toString().trim();
        String passworNewConfirm = passwordNewConfirmEditText.getText().toString().trim();

        UserChangePasswordDTO dto = new UserChangePasswordDTO();
        dto.setPhone(phone);
        dto.setOldPassword(passworOld);
        dto.setNewPassword(passworNew);
        dto.setNewPasswordConfirm(passworNewConfirm);

        try {
            Result result = userService.changePassword(dto);
            if(result.isSuccess()){
                Toast.makeText(ResetpwdActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent_Register_to_Login = new Intent(ResetpwdActivity.this,LoginActivity.class) ;    //切换User Activity至Login Activity
//                        startActivity(intent_Register_to_Login);
//                        finish();
//                    }
//                }, 1000);
            } else {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
        }

//            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd_old);
//            if(result==1){                                             //返回1说明用户名和密码均正确,继续后续操作
//                if(userPwd_new.equals(userPwdCheck)==false){           //两次密码输入不一样
//                    Toast.makeText(this, getString(R.string.pwd_not_the_same),Toast.LENGTH_SHORT).show();
//                    return ;
//                } else {
//                    UserData mUser = new UserData(userName, userPwd_new);
//                    mUserDataManager.openDataBase();
//                    boolean flag = mUserDataManager.updateUserData(mUser);
//                    if (flag == false) {
//                        Toast.makeText(this, getString(R.string.resetpwd_fail),Toast.LENGTH_SHORT).show();
//                    }else{
//
//                        Toast.makeText(this, getString(R.string.resetpwd_success),Toast.LENGTH_SHORT).show();
//
//                        mUser.pwdresetFlag=1;
//                        Intent intent_Register_to_Login = new Intent(ResetpwdActivity.this,LoginActivity.class) ;    //切换User Activity至Login Activity
//                        startActivity(intent_Register_to_Login);
//                        finish();
//                    }
//                }
//            }else if(result==0){                                       //返回0说明用户名和密码不匹配，重新输入
//                Toast.makeText(this, getString(R.string.pwd_not_fit_user),Toast.LENGTH_SHORT).show();
//                return;
//            }
    }

}

