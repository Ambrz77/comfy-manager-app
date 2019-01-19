package com.example.alireza.taskmanager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    static int x=0;
    String a = null;
    String b = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent NewIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(NewIntent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
