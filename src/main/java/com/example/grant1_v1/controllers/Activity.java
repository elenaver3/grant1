package com.example.grant1_v1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Activity {

    int activityId;
    String activityName;
    String date;
    String time;

    String direction;



    public Activity(int activityId, String activityName, String date, String time, String direction) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.date = date;
        this.time = time;
        this.direction = direction;
    }
    public Activity(ResultSet resultSet) {
        try {
            this.activityId = resultSet.getInt(1);
            this.activityName = resultSet.getString(2);
            this.date = resultSet.getString(3);
            this.time = resultSet.getString(4);
            this.direction = resultSet.getString(5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
