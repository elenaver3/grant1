package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class JuryController {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button buttonActivity;

    @FXML
    private Button buttonProfile;

    @FXML
    private Button goBack;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private ImageView imageView_photo;

    @FXML
    private Label label_name;

    @FXML
    private Label label_welcome;


    @FXML
    void buttonActivity(ActionEvent event) {

    }

    @FXML
    void buttonProfile(ActionEvent event) {
        HelloApplication.changeMainPage("profile.fxml", new ProfileController(user));

    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("authorization.fxml", new AuthController());
    }

    @FXML
    public void initialize() {
        if (user.getGender().equals("Ж"))
            label_name.setText("Ms " + user.getName());
        else
            label_name.setText("Mr " + user.getName());
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 9 && calendar.get(Calendar.HOUR_OF_DAY) <= 11) {
            if (calendar.get(Calendar.HOUR_OF_DAY) == 11 && calendar.get(Calendar.MINUTE) > 0)
                label_welcome.setText("Добрый день!");
            else
                label_welcome.setText("Доброе утро!");
        }
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 11 && calendar.get(Calendar.HOUR_OF_DAY) <= 18 ) {
            if (calendar.get(Calendar.HOUR_OF_DAY) == 18 && calendar.get(Calendar.MINUTE) > 0)
                label_welcome.setText("Добрый вечер!");
            else
                label_welcome.setText("Добрый день!");
        }
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 18 || calendar.get(Calendar.HOUR_OF_DAY) == 0) {
            if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) > 0)
                label_welcome.setText("Доброй ночи!");
            else
                label_welcome.setText("Добрый вечер!");
        }
        if (calendar.get(Calendar.HOUR_OF_DAY) < 9) {
            if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0)
                label_welcome.setText("Добрый вечер!");
            else
                label_welcome.setText("Доброй ночи!");
        }



    }

    public JuryController(User user) {
        this.user = user;
    }

    public JuryController() {

    }


}
