package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainController {

    private User profiled;

    public User getProfile() {
        return profiled;
    }

    public void setProfile(User profile) {
        this.profiled = profile;
    }

    @FXML
    private Button buttonBack;

    @FXML
    private Button button_event;

    @FXML
    private Button button_signIn;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private Label profile;

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("authorization.fxml");
    }

    @FXML
    public void initialize() {
        setProfile(profiled);
    }
    public MainController(User user){
        profiled = user;
    }

}
