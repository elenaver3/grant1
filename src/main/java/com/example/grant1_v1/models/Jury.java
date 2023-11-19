package com.example.grant1_v1.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Jury {
    String name;
    String accessLevel;
    String mail;
    String photo;

    String activity;
    public Jury(String name, String accessLevel, String mail, String photo, String activity) {
        this.name = name;
        this.accessLevel = accessLevel;
        this.mail = mail;
        this.photo = photo;
        this.activity = activity;
    }
    public Jury(ResultSet resultSet) {
        try {
            this.name = resultSet.getString(1);
            this.accessLevel = resultSet.getString(2);
            this.mail = resultSet.getString(3);
            this.photo = resultSet.getString(4);
            this.activity = resultSet.getString(5);
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

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
