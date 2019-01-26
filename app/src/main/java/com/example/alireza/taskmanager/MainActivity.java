package com.example.alireza.taskmanager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Intent for Login Page Test:
        Intent NewIntent = new Intent(getApplicationContext(), NewPriority.class);
        startActivity(NewIntent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button SignInbtn = (Button) findViewById(R.id.LoginButton);
        Button SignUpbtn = (Button) findViewById(R.id.RegisterButton);


        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NewIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(NewIntent);
            }
        });
        SignInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NewIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(NewIntent);
            }
        });

    }
}
