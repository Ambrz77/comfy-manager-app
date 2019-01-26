package com.example.alireza.taskmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener,AdapterView.OnItemSelectedListener {
    Button TimePick, DatePick, DoneButton;
    TextView tv_result;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal = 0, hourFinal, minuteFinal;
    String user;
    String detail = null, subject = null,p;
    Priority priority;
    Server server = new Server();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            user = extras.getString("user");

        EditText sub = findViewById(R.id.title);
        EditText des = findViewById(R.id.des);

        TimePick = (Button)findViewById(R.id.TimePick);
        DatePick = (Button)findViewById(R.id.DatePick);
        DoneButton = (Button)findViewById(R.id.DoneButton);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(AddTask.this,
                android.R.layout.simple_spinner_item,server.getUser(user).getPriorityName());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        DatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTask.this, AddTask.this, year, month, day);
                datePickerDialog.show();
            }
        });

        TimePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddTask.this, AddTask.this, hour, minute, true);
                timePickerDialog.show();
            }
        });
        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*detail = des.getText().toString();
                subject = sub.getText().toString();
                Task task = new Task(subject,server.getUser(user).getThisPriority(spinner.getSelectedItem().toString()));
                task.setDetail(detail);
                task.setTime(yearFinal,monthFinal,dayFinal,hourFinal,minuteFinal);
                server.getUser(user).tasks.add(task);*/
                Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        hourFinal = hourOfDay;
        minuteFinal = minute;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month;
        dayFinal = dayOfMonth;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
        DoneButton.setEnabled(false);
    }
}
