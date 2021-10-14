/*
Assignment: Home Work Assignment Number 2
File Name: MainActivity.java
Group Number 19
Full Name:
Nisha Ramrakhyani
Punit Mashruwala */
package com.example.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final static public int Create_REQ_CODE = 1;
    final static public int Display_REQ_CODE = 100;
    final static public String TAG = "demo";
    final static public String TASK_KEY = "TASK";
    final static public String TASK_Index_KEY = "TASK_INDEX";
    public ArrayList<task> taskList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.TODOLIST));
        Button buttonCreateTask = findViewById(R.id.buttonCreateTask);
        Button viewButtonTask = findViewById(R.id.button_ViewTasks);

        buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTask.class);
                startActivityForResult(intent, Create_REQ_CODE);
            }
        });

        viewButtonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("View Task");
                int list_size = taskList.size();
                if (list_size > 0) {
                    String[] taskName = new String[list_size];
                    for (int i = 0; i < list_size; i++) {
                        taskName[i] = (taskList.get(i).name);
                    }
                    builder.setItems(taskName, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, DisplayTask.class);
                            task taskName = taskList.get(which);
                            intent.putExtra(TASK_KEY, new task(taskName.name, taskName.priority, taskName.date));
                            intent.putExtra(TASK_Index_KEY, which);
                            startActivityForResult(intent, Display_REQ_CODE);
                        }
                    });
                } else {
                    builder.setMessage(getResources().getString(R.string.viewMessage));
                }
                builder.setNegativeButton(getResources().getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    // No code
                    }
                });
                builder.create().show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Create_REQ_CODE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra(CreateTask.TASK_NAME_KEY) && data.hasExtra(CreateTask.TASK_DATE_KEY) && data.hasExtra(CreateTask.TASK_PRIORITY_KEY)) {
                String name = data.getStringExtra(CreateTask.TASK_NAME_KEY);
                String in_date = data.getStringExtra(CreateTask.TASK_DATE_KEY);
                String priority = data.getStringExtra(CreateTask.TASK_PRIORITY_KEY);
                // Date format
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                Date date = null;
                try {
                    date = format.parse(in_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat formatter5 = new SimpleDateFormat("MM/dd/yyyy");
                String final_date = formatter5.format(date);

                task task_1 = new task(name, priority, final_date);
                taskList.add(task_1);
                onChangeChangeDisplay();
            }

        } else if (requestCode == Display_REQ_CODE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra(DisplayTask.D_Index_KEY)) {
                int index = data.getIntExtra(DisplayTask.D_Index_KEY, -1);
                if (index != -1) {
                    taskList.remove(data.getIntExtra(DisplayTask.D_Index_KEY, -1));
                    onChangeChangeDisplay();
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.Delete_Toast), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.Delete_U_Toast), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.Delete_U_Toast), Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.Cancel_Toast), Toast.LENGTH_LONG).show();
        }
    }

    private void onChangeChangeDisplay() {
        TextView textViewTaskCount = findViewById(R.id.textViewTaskCount);
        TextView textViewRetrieveTask = findViewById(R.id.textViewRetrieveTask);
        TextView textViewRetrieveDate = findViewById(R.id.textViewRetrieveDate);
        TextView textViewRetrievePriority = findViewById(R.id.textViewRetrievePriority);
        if (taskList.size() > 0) {
            String count = getResources().getString(R.string.No_of_tasks) + " " + taskList.size() + " " + getResources().getString(R.string.task);
            textViewTaskCount.setText(count);

            Collections.sort(taskList, new Comparator<task>() {
                @Override
                public int compare(task o1, task o2) {
                    return (o1.date).compareTo(o2.date);
                }
            });

            textViewRetrieveTask.setText(taskList.get(0).name);
            textViewRetrieveDate.setText((taskList.get(0).date));
            textViewRetrievePriority.setText(taskList.get(0).priority);
        } else {
            textViewTaskCount.setText(getResources().getString(R.string.No_of_tasks_0));
            textViewRetrieveTask.setText(getResources().getString(R.string.empty));
            textViewRetrieveDate.setText(getResources().getString(R.string.empty));
            textViewRetrievePriority.setText(getResources().getString(R.string.empty));
        }
    }
}

