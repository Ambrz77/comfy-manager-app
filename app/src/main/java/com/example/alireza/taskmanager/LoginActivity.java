package com.example.alireza.taskmanager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.FileObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Server server = new Server();
    String user_email,password;
    EditText ue,pw;


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            textChecklogin();
        }
    };

    public void textChecklogin() {
        Button login = (Button) findViewById(R.id.loginbt);
        user_email = ue.getText().toString();
        password = pw.getText().toString();
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNum = password.contains("9") || password.contains("8") || password.contains("7") || password.contains("6") || password.contains("5") || password.contains("4") || password.contains("3") || password.contains("2") || password.contains("1") || password.contains("0");
        boolean psize = password.length()>=8;
        boolean passok = hasLowercase && hasUppercase && hasNum && psize;
        if (!passok || user_email.equals("")) {
            login.setEnabled(false);
        } else {
            login.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextView LoginError = (TextView) findViewById(R.id.LoginError);

        //if (Correct data)
        //LoginError.setText(""); ==> No need!
        //else
        //LoginError.setText("Wrong data, Please try again!");

        Button login = (Button) findViewById(R.id.loginbt);
        ue = findViewById(R.id.ueText);
        pw = findViewById(R.id.passwordText);
        ue.addTextChangedListener(textWatcher);
        pw.addTextChangedListener(textWatcher);

        textChecklogin();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email = ue.getText().toString();
                password = pw.getText().toString();
                try {
                    if ((server.isUser(user_email) == 1 || server.isUser(user_email) == 2) && server.login(user_email, password)) {
                        System.out.println(user_email + " logged in!");
                        Intent Lintent = new Intent(LoginActivity.this, HomeActivity.class);
                        Lintent.putExtra("user", user_email);
                        startActivity(Lintent);
                    } else {
                        throw new UnknownUserException(user_email + " isn't registered!");
                    }
                } catch (UnknownUserException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
