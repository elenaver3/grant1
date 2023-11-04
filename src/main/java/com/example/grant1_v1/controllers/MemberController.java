package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MemberController {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private TextField eventField;

    @FXML
    private Button goBack;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private TableView<?> tableViewMember;

    @FXML
    void eventField(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("organizer.fxml", new OrganizerController(user));
    }

    public MemberController(User user) {
        this.user = user;
    }

    public MemberController() {

    }
}
