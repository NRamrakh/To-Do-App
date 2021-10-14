/*
Assignment: Home Work Assignment Number 2
File Name: DisplayTask.java
Group Number 19
Full Name:
Nisha Ramrakhyani
Punit Mashruwala */
package com.example.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayTask extends AppCompatActivity {
    final static public String D_Index_KEY = "D_TASK_INDEX";
    public int index = -1;
    TextView textViewTaskName;
    TextView textViewTaskDate;
    TextView textViewPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task);
        setTitle(getResources().getString(R.string.DisplayPage));

        textViewTaskName = findViewById(R.id.textViewName);
        textViewTaskDate = findViewById(R.id.textViewDate);
        textViewPriority = findViewById(R.id.textViewPriority);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(MainActivity.TASK_KEY)) {
            task user = (task) getIntent().getSerializableExtra(MainActivity.TASK_KEY);
            textViewTaskName.setText(user.name);
            textViewTaskDate.setText((user.date));
            textViewPriority.setText(user.priority);
        }
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(MainActivity.TASK_Index_KEY)) {
            index = getIntent().getIntExtra(MainActivity.TASK_Index_KEY, -1);
        }
        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DisplayTask.this);
                builder.setTitle(getResources().getString(R.string.deleteTitle));
                builder.setMessage(getResources().getString(R.string.deleteMessage));
                builder.setPositiveButton(getResources().getString(R.string.Delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(D_Index_KEY, index);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // No code
                        Toast.makeText(DisplayTask.this, getResources().getString(R.string.Cancel_Toast), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
    }
}