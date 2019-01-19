package com.example.alireza.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {

    Server server=new Server();
    String user,email,name,family,pass;
    int type=0;
    EditText u,e,n,f,p;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            textCheck();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button signup = (Button) findViewById(R.id.signup);
        u = findViewById(R.id.userText);
        e = findViewById(R.id.emailText);
        n = findViewById(R.id.nameText);
        f = findViewById(R.id.familyText);
        p = findViewById(R.id.passText);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = u.getText().toString();
                email = e.getText().toString();
                name = n.getText().toString();
                family = f.getText().toString();
                pass = p.getText().toString();
                try {
                    if (!(server.isUser(user) == 1 || server.isUser(email) == 2)) {
                        server.Register(user, email, name, pass, family, type);
                        System.out.println(u + " Registered!");
                        Intent Hintent = new Intent(RegisterActivity.this, HomeActivity.class);
                    } else {
                        throw new ExistedUserException(u + " is already registered!");
                    }
                } catch (ExistedUserException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void textCheck() {
        Button signup = (Button) findViewById(R.id.signup);
        user = u.getText().toString();
        email = e.getText().toString();
        name = n.getText().toString();
        pass = p.getText().toString();
        boolean hasUppercase = !pass.equals(pass.toLowerCase());
        boolean hasLowercase = !pass.equals(pass.toUpperCase());
        boolean hasNum = pass.contains("9") || pass.contains("8") || pass.contains("7") || pass.contains("6") || pass.contains("5") || pass.contains("4") || pass.contains("3") || pass.contains("2") || pass.contains("1") || pass.contains("0");
        boolean psize = pass.length()>=8;
        boolean passok = hasLowercase && hasUppercase && hasNum && psize;
        if (!passok || u.equals("") || !server.isValidEmail(email) || name.equals("")) {
            signup.setEnabled(false);
        } else {
            signup.setEnabled(true);
        }
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
