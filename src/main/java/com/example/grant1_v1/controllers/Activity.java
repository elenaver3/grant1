package com.example.grant1_v1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Activity {
    String activity_name;
    String date;
    String time;



    public Activity(String activity_name, String date, String time) {
        this.activity_name = activity_name;
        this.date = date;
        this.time = time;
    }
    public Activity(ResultSet resultSet) {
        try {
            this.activity_name = resultSet.getString(1);
            this.date = resultSet.getString(2);
            this.time = resultSet.getString(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
