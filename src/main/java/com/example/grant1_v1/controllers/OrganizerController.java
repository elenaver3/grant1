package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.Organizer;
import com.example.grant1_v1.models.Query;
import com.example.grant1_v1.models.Regex;
import com.example.grant1_v1.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrganizerController {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button buttonEvent;

    @FXML
    private Button buttonJuri;

    @FXML
    private Button buttonMember;

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
    void buttonEvent(ActionEvent event) {
        HelloApplication.changeMainPage("event_information.fxml", new EventsController());
    }

    @FXML
    void buttonJuri(ActionEvent event) {
        HelloApplication.changeMainPage("organizer_jury.fxml", new JuryListController(user));
    }

    @FXML
    void buttonMember(ActionEvent event) {
        HelloApplication.changeMainPage("organizer_member.fxml", new EventsController());
    }

    @FXML
    void buttonProfile(ActionEvent event) {
        HelloApplication.changeMainPage("profile.fxml", new ProfileController(user));
    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("authorization.fxml", new AuthController());

    }

    public OrganizerController(User user) {
        this.user = user;
    }

    public OrganizerController() {

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
}
