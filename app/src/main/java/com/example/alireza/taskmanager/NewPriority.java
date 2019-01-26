package com.example.alireza.taskmanager;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class NewPriority extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    String user;
    EditText pr;
    int progress_value;
    Server server = new Server();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_priority);
        pr = findViewById(R.id.pr);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            user = extras.getString("user");
        {
            seekBar = (SeekBar)findViewById(R.id.seekBar);
            textView = (TextView)findViewById(R.id.MyMail);
            textView.setText(seekBar.getProgress() + " / " + seekBar.getMax());


            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progress_value=progress;
                    textView.setText(progress + " / " + seekBar.getMax());
                    Toast.makeText(NewPriority.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    Toast.makeText(NewPriority.this, "Start Tracking", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    textView.setText(progress_value + " / " + seekBar.getMax());
                    Toast.makeText(NewPriority.this, "Stoped Tracking", Toast.LENGTH_LONG).show();
                }
            });
        }
        server.getUser(user).priorities.add(new Priority(pr.getText().toString(), progress_value));
    }

}
