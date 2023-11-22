package com.example.grant1_v1.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query {
    public static final String checkAuth = "SELECT 1 FROM auth WHERE mail=? AND password =?";
    public static final String createUser = "SELECT mail,name,accessLevel FROM employee WHERE mail=?";
    public static final String auth_log = "test@mail.ru";
    public static final String auth_psw = "Username1234!";

    public static final String registerUser = "INSERT INTO user(name,gender,mail,birthday,city,telephone)\n" +
            "VALUES\n" +
            "(?,?,?,?,?,?);\n";

    public static final String getUser = "SELECT * FROM user WHERE id = ? ";

    public static final String registerAuthorizationData = "INSERT INTO authentication(mail,password)\n" +
            "VALUES (?,?);";
}
