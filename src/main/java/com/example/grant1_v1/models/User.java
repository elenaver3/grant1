package com.example.grant1_v1.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    int id;
    private String name;
    private String mail;
    private int accessLevel;
    private String gender;
    public User(String mail){
        try {
            PreparedStatement query = DBConnect.getDBConnect().getConnection().prepareStatement(Query.createUser);
            query.setString(1,mail);
            ResultSet result = query.executeQuery();
            if(result.next()){
                id = result.getInt(1);
                name = result.getString(2);
                gender = result.getString(3);
                mail = result.getString(4);
                accessLevel = result.getInt(11);
            }else{
                System.out.println("#");
            }
        } catch (SQLException e) {
            System.out.println("User_Enter_Trouble");
            throw new RuntimeException(e);
        }

    }

    public User() {
        this.mail = "";
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
