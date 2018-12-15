package garage.wangyu.com.garageandroid.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class UserActivity extends AppCompatActivity {
    private Button mReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mReturnButton = (Button)findViewById(R.id.returnback);

    }
    public void back_to_login(View view) {
        //setContentView(R.layout.login);
        Intent intent3 = new Intent(UserActivity.this,LoginActivity.class) ;
        startActivity(intent3);
        finish();

    }
}
