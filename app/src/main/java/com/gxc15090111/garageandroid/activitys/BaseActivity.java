package com.gxc15090111.garageandroid.activitys;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.gxc15090111.garageandroid.service.UserService;

public class BaseActivity extends AppCompatActivity {

    protected Handler handler = new Handler();

    protected UserService userService = UserService.getInstance();

    protected SharedPreferences login_sp;

    protected DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected boolean isDevelop(){
        return UserService.isDevelop;
    }

}
