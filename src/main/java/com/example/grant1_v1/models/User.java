package com.example.grant1_v1.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String name;
    private String mail;
    private int roleId;
    public User(String mail){
        try {
            PreparedStatement query = DBConnect.getDBConnect().getConnection().prepareStatement(Query.createUser);
            query.setString(1,mail);
            ResultSet result = query.executeQuery();
            if(result.next()){
                name = result.getString(1);
                System.out.println(name);
                mail = result.getString(2);
                System.out.println(mail);
                roleId = result.getInt(3);
                System.out.println(roleId);
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
}
