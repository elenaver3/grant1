package com.example.grant1_v1.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyEvent {


    String eventName;
    String subdirection;
    String startDate;
    String logo;
    String eventId;
    String organaizerName;

    String endDate;
    String description;
    String cityName;





    public MyEvent(String eventName, String activityName, String startDate, String logo) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.logo = logo;
    }
    public MyEvent(ResultSet resultSet) {
        try {
            this.eventId = resultSet.getString(1);
            this.eventName = resultSet.getString(2);
            this.organaizerName = resultSet.getString(3);
            this.startDate = resultSet.getString(4);
            this.endDate = resultSet.getString(5);
            this.cityName = resultSet.getString(6);
            this.logo = resultSet.getString(7);
            this.description = resultSet.getString(8);
            this.subdirection = resultSet.getString(9);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getOrganaizerName() {
        return organaizerName;
    }

    public void setOrganaizerName(String organaizerName) {
        this.organaizerName = organaizerName;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSubdirection() {
        return subdirection;
    }

    public void setSubdirection(String subdirection) {
        this.subdirection = subdirection;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
