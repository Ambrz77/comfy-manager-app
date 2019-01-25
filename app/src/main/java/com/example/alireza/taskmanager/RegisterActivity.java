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
import android.widget.RadioGroup;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final TextView RegisterError = (TextView) findViewById(R.id.RegError);

        Button signup = (Button) findViewById(R.id.signup);
        u = findViewById(R.id.userText);
        e = findViewById(R.id.emailText);
        n = findViewById(R.id.nameText);
        f = findViewById(R.id.familyText);
        p = findViewById(R.id.passwordText);

        u.addTextChangedListener(textWatcher);
        e.addTextChangedListener(textWatcher);
        n.addTextChangedListener(textWatcher);
        p.addTextChangedListener(textWatcher);

        textCheck();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.NormalButton:
                        type=1;
                        break;
                    case R.id.SilverButton:
                        type=2;
                        break;
                    case R.id.GoldButton:
                        type=3;
                        break;
                }
            }
        });

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
                        RegisterError.setText(u+ "Registered!");
                        Intent Hintent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(Hintent);
                    } else {
                        RegisterError.setText("Wrong data, Please try again!");
                        throw new ExistedUserException(u + " is already registered!");
                    }
                } catch (ExistedUserException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
