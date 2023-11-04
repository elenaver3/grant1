package com.example.grant1_v1.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Member {

    String name;
    String accessLevel;
    String mail;
    String photo;
    public Member(String name, String accessLevel, String mail, String photo) {
        this.name = name;
        this.accessLevel = accessLevel;
        this.mail = mail;
        this.photo = photo;
    }
    public Member(ResultSet resultSet) {
        try {
            this.name = resultSet.getString(1);
            this.accessLevel = resultSet.getString(2);
            this.mail = resultSet.getString(3);
            this.photo = resultSet.getString(4);
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
}
