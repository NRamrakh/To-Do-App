package com.example.todoapp;

import java.io.Serializable;

public class task implements Serializable {
    String name;
    String priority;
    String date;


    public task(String name, String priority, String date) {
        this.name = name;
        this.priority = priority;
        this.date = date;
    }

    @Override
    public String toString() {
        return "task{" +
                "name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}
