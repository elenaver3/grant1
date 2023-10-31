package com.example.grant1_v1.models;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private static DBConnect dbConnect;
    private static Connection connection;

    private DBConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2277_grant1", "std_2277_grant1", "grant1Pass");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnect getDBConnect() {
        if (dbConnect == null) {
            dbConnect = new DBConnect();
        }
        return dbConnect;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeQuery(String query) {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);

            return result;
        } catch (SQLException e) {
            System.out.println("Query Error");
            throw new RuntimeException(e);
        }
    }
    public static ResultSet executePreparedQuery(String queryString, ArrayList<String> parameters){
        try {
            PreparedStatement query = DBConnect.getDBConnect().getConnection().prepareStatement(queryString);
            for (int i = 0; i < parameters.size(); i++) {
                query.setString(i+1, parameters.get(i));
            }
            return  query.executeQuery();
        }catch (SQLException e){
            System.out.println("Parametrized Query Error");
            throw new RuntimeException(e);
        }
    }
}


