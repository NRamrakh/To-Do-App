/*
Assignment: Home Work Assignment Number 2
File Name: CreateTask.java
Group Number 19
Full Name:
Nisha Ramrakhyani
Punit Mashruwala */
package com.example.todoapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateTask extends AppCompatActivity {
    final static public String TASK_KEY = "TASK";
    final static public String TASK_NAME_KEY = "TASK_NAME";
    final static public String TASK_DATE_KEY = "TASK_DATE";
    final static public String TASK_PRIORITY_KEY = "TASK_PRIORITY";
    static public int month;
    static public int year;
    static public int c_date;
    static public Boolean Error_Flag = Boolean.TRUE;
    EditText name;
    RadioGroup radioGroup;
    TextView textViewCreateDate;
    Button submitButton;
    Button dateSelectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        setTitle(getResources().getString(R.string.CreateTaskPage));

        dateSelectButton = findViewById(R.id.buttonSetDate);
        submitButton = findViewById(R.id.buttonSubmit);
        textViewCreateDate = findViewById(R.id.textViewCreateDate);
        radioGroup = findViewById(R.id.radioGroup);
        name = findViewById(R.id.editTextName);

        dateSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                month = currentDate.get(Calendar.MONTH);
                year = currentDate.get(Calendar.YEAR);
                c_date = currentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker = new DatePickerDialog(CreateTask.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedYear, selectedMonth, selectedDay);

                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                        Date date = null;
                        try {
                            date = sdf.parse(DateFormat.getDateInstance().format(newDate.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                        String dateSt = sdf1.format(date.getTime());
                        newDate.setTime(date);
                        year = Calendar.YEAR;
                        month = Calendar.MONTH;
                        c_date = Calendar.DATE;
                        textViewCreateDate.setText(dateSt);

                    }
                }, year, month, c_date);

                mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDatePicker.setTitle(getResources().getString(R.string.DatePicker));
                mDatePicker.show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priority = "";
                String task_name = name.getText().toString();
                String date = textViewCreateDate.getText().toString();

                if (task_name.isEmpty()) {
                    Error_Flag = Boolean.TRUE;
                    Toast.makeText(CreateTask.this, getResources().getString(R.string.name_error), Toast.LENGTH_SHORT).show();
                } else if (date.isEmpty()) {
                    Error_Flag = Boolean.TRUE;
                    Toast.makeText(CreateTask.this, getResources().getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                } else {
                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    if (checkedId == R.id.radioButtonLow) {
                        priority = "Low";
                        Error_Flag = Boolean.FALSE;
                    } else if (checkedId == R.id.radioButtonMedium) {
                        priority = "Medium";
                        Error_Flag = Boolean.FALSE;
                    } else if (checkedId == R.id.radioButtonHigh) {
                        priority = "High";
                        Error_Flag = Boolean.FALSE;
                    } else {
                        Error_Flag = Boolean.TRUE;
                        Toast.makeText(CreateTask.this, getResources().getString(R.string.priority_error), Toast.LENGTH_SHORT).show();
                    }
                }

                if (!Error_Flag) {
                    Intent intent = new Intent();
//                    intent.putExtra(TASK_KEY, new task(task_name, priority, date));
                    intent.putExtra(TASK_NAME_KEY, task_name);
                    intent.putExtra(TASK_PRIORITY_KEY, priority);
                    intent.putExtra(TASK_DATE_KEY, date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}