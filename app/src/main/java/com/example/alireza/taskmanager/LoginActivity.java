package com.example.alireza.taskmanager;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextView LoginError = (TextView) findViewById(R.id.LoginError);

        //if (Correct data)
        //LoginError.setText(""); ==> No need!
        //else
        LoginError.setText("Wrong data, Please try again!");
    }
}
