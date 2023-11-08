package com.example.grant1_v1.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query {
    public static final String checkAuth = "SELECT 1 FROM auth WHERE userID=? AND password =?";
    public static final String createUser = "SELECT * FROM user WHERE id=?";

    public static final String getEvents = "SELECT event.id,event.name,user.name,`startDate`,`endDate`,city.name,`logo`,`description`,user.subdirection FROM `event` JOIN `city` ON event.city=city.id JOIN organaizer ON organaizerID = organaizer.id JOIN user ON user.id=organaizer.userID";
    public static final String getJuryies = "SELECT user.name, mail, photo, accesslevel.name FROM user JOIN accesslevel ON accesslevel.id=user.accessLevel WHERE accessLevel IN (2, 3)";
    public static final String getRole = "SELECT accessLevel FROM user WHERE id=?";
    public static final String getMembers = "SELECT user.name, mail, photo, accesslevel.name FROM user JOIN accesslevel ON accesslevel.id=user.accessLevel WHERE accessLevel = 4";
    public static final String getMembersCount = "SELECT COUNT(user.name) FROM user JOIN accesslevel ON accesslevel.id=user.accessLevel WHERE accessLevel = 4";
    public static final String getActivities = "SELECT activity.name AS activity, event.startDate AS date, activity.time FROM event JOIN activity ON activity.eventID=event.id;";


    }
