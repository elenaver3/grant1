package com.example.grant1_v1.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query {
    public static final String checkAuth = "SELECT 1 FROM auth WHERE mail=? AND password =?";
    public static final String createUser = "SELECT * FROM user WHERE mail=?";

    public static final String auth_log = "test@mail.ru";
    public static final String auth_psw = "Username1234!";

    public static final String getEvents = "SELECT event.name AS event, activity.name AS activity, startDate, logo FROM event JOIN activity ON activity.eventID=event.id;";
    public static final String getJuryies = "SELECT user.name, mail, photo, accesslevel.name FROM user JOIN accesslevel ON accesslevel.id=user.accessLevel WHERE accessLevel IN (2, 3)";
    public static final String getRole = "SELECT accessLevel FROM user WHERE mail=?";
    public static final String getMembers = "SELECT user.name, mail, photo, accesslevel.name FROM user JOIN accesslevel ON accesslevel.id=user.accessLevel WHERE accessLevel = 4";
    public static final String getActivities = "SELECT activity.name AS activity, event.startDate AS date, activity.time FROM event JOIN activity ON activity.eventID=event.id;";


    }
