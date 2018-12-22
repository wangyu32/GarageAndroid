package garage.wangyu.com.garageandroid.activitys;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import garage.wangyu.com.garageandroid.service.UserService;

public class BaseActivity extends AppCompatActivity {

    protected Handler handler = new Handler();

    protected UserService userService = new UserService();

    protected SharedPreferences login_sp;

    protected DateFormat dateFormator = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

}
