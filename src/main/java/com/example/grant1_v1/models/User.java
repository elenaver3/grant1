package com.example.grant1_v1.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String name;
    private String mail;
    private int roleId;
    private String gender;
    public User(String mail){
        try {
            PreparedStatement query = DBConnect.getDBConnect().getConnection().prepareStatement(Query.createUser);
            query.setString(1,mail);
            ResultSet result = query.executeQuery();
            if(result.next()){
                name = result.getString(2);
                gender = result.getString(3);
                mail = result.getString(4);
                roleId = result.getInt(11);
            }else{
                System.out.println("#");
            }
        } catch (SQLException e) {
            System.out.println("User_Enter_Trouble");
            throw new RuntimeException(e);
        }

    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
