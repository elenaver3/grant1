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

    @FXML
    private Button buttonBack;

    @FXML
    private Button button_event;

    @FXML
    private Button button_signIn;

    @FXML
    private ImageView imageView_logo;

    @FXML
    void goBack(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void openAuth(ActionEvent event) {
        HelloApplication.changeMainPage("authorization.fxml", new AuthController());
    }

    @FXML
    void openEvents(ActionEvent event) {
        HelloApplication.changeMainPage("event.fxml", new EventsController());
    }

    @FXML
    public void initialize() {

    }


    public MainController(){

    }


}
