package com.example.grant1_v1.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Event {

    String name;
    String startDate;
    String logo;


    public Event(String name, String startDate, String logo) {
        this.name = name;
        this.startDate = startDate;
        this.logo = logo;
    }
    public Event(ResultSet resultSet) {
        try {
            this.name = resultSet.getString(1);
            this.startDate = resultSet.getString(2);
            this.logo = resultSet.getString(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
