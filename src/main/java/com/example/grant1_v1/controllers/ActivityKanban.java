package com.example.grant1_v1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityKanban {

    int activityId;
    String activityName;
    String time;


    String status;



    public ActivityKanban(int activityId, String activityName, String time, String status) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.time = time;
        this.status = status;

    }
    public ActivityKanban(ResultSet resultSet) {
        try {
            this.activityId = resultSet.getInt(1);
            this.activityName = resultSet.getString(2);
            this.time = resultSet.getString(3);
            this.status = resultSet.getString(4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
