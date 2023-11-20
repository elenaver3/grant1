package com.example.grant1_v1.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query {
    public static final String checkAuth = "SELECT 1 FROM auth WHERE userID=? AND password =?";
    public static final String createUser = "SELECT * FROM user WHERE id=?";

    public static final String getEvents = "SELECT event.id,event.name,user.name,`startDate`,`endDate`,city.name,`logo`,`description`,user.subdirection FROM `event` JOIN `city` ON event.city=city.id JOIN organaizer ON organaizerID = organaizer.id JOIN user ON user.id=organaizer.userID";
    public static final String getJuryiesAndModerators = "SELECT user.name, mail, photo, accesslevel.name, activity.name AS activity\n" +
                                                    "FROM user JOIN accesslevel ON accesslevel.id=user.accessLevel\n" +
                                                    "JOIN activity ON user.id=activity.moderatorID OR user.id=activity.juryID\n" +
                                                    "WHERE accessLevel IN (2, 3);";

    public static final String getJuryiesAndModeratorsCount = "SELECT COUNT(user.id)\n" +
                                                                "FROM user\n" +
                                                                "WHERE accessLevel IN (2, 3);";
    public static final String getRole = "SELECT accessLevel FROM user WHERE id=?";
    public static final String getMembers = "SELECT user.name, mail, photo, accesslevel.name FROM user JOIN accesslevel ON accesslevel.id=user.accessLevel WHERE accessLevel = 4";
    public static final String getMembersCount = "SELECT COUNT(user.name) FROM user JOIN accesslevel ON accesslevel.id=user.accessLevel WHERE accessLevel = 4";
    public static final String getActivities = "SELECT activity.id, activity.name AS activity, event.startDate AS date, activity.time, event.name AS direction\n" +
            "FROM event JOIN activity ON activity.eventID=event.id;";

    public static final String updateModeratorOnActivity = "UPDATE `activity` \n" +
                                                            "SET `moderatorID`=?\n" +
                                                            "WHERE `id`=?;";


    }
