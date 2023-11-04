package com.example.grant1_v1.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Event {

    String event_name;
    String startDate;
    String logo;
    String activity_name;



    public Event(String event_name, String activity_name, String startDate, String logo) {
        this.event_name = event_name;
        this.activity_name = activity_name;
        this.startDate = startDate;
        this.logo = logo;
    }
    public Event(ResultSet resultSet) {
        try {
            this.event_name = resultSet.getString(1);
            this.activity_name = resultSet.getString(2);
            this.startDate = resultSet.getString(3);
            this.logo = resultSet.getString(4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }
}
