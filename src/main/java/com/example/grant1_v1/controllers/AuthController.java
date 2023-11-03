package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class AuthController {

    private User profile;

    public User getProfile() {
        return profile;
    }

    public void setProfile(User profile) {
        this.profile = profile;
    }

    @FXML
    private Button buttonBack;

    @FXML
    private CheckBox checkBoxRemember;

    @FXML
    private CheckBox checkBoxShowPassword;

    @FXML
    private Button enter;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private TextField log;

    @FXML
    private PasswordField psw;

    @FXML
    private Button register;

    @FXML
    public void initialize() {
        Regex.addListenerFormatter(log, Regex.getCheckLog());
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        log.setText(Query.auth_log);
        psw.setText(Query.auth_psw);
        psw.setText(Query.auth_psw);
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    @FXML
    void enter(ActionEvent event) {
        if (Regex.checkName(log.getText(), Regex.getCheckLog()) & Regex.checkName(psw.getText(), Regex.getCheckPsw())) {
            try {
                ResultSet resultSet = DBConnect.executePreparedQuery(Query.checkAuth,new ArrayList<String>(Arrays.asList(log.getText(),PasswordHashing.hashPassword(psw.getText()))));
                if (resultSet.next()) {
                    if (resultSet.getString(1).equals("1")) {
                        resultSet = DBConnect.executePreparedQuery(Query.getRole,new ArrayList<String>(Arrays.asList(log.getText())));
                        if (resultSet.next()) {
                            profile = new User(log.getText());

                            if (resultSet.getString(1).equals("1")) {
                                HelloApplication.changeMainPage("organizer.fxml", new OrganizerController(profile));
                            }
                            else {
                                if (resultSet.getString(1).equals("2")) {
                                    HelloApplication.changeMainPage("moderator.fxml", new ModeratorController(profile));
                                }
                                else {
                                    if (resultSet.getString(1).equals("3")) {
                                        HelloApplication.changeMainPage("jury.fxml", new JuryController(profile));
                                    }
                                    else {
                                        HelloApplication.changeMainPage("participant.fxml", new ParticipantController());
                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("e");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void register(ActionEvent event) {
        HelloApplication.changeMainPage("registration.fxml");
    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("main.fxml", new MainController());
    }

}
