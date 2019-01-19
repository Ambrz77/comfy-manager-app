package com.example.alireza.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {

    String user,email,name,family,pass;
    int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRadioButtonClicked(View view) {
        boolean clicked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.NormalButton:
                if (clicked) {
                    type=1;
                }
                break;
            case R.id.SilverButton:
                if (clicked) {
                    type=2;
                }
                break;
            case R.id.GoldButton:
                if (clicked) {
                    type=3;
                }
                break;
        }
    }
}
