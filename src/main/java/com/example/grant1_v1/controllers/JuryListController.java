package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.Jury;
import com.example.grant1_v1.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class JuryListController {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private TextField filterEvent;

    @FXML
    private TextField filterF;

    @FXML
    private Button goBack;

    @FXML
    private Text labelCount;

    @FXML
    private ImageView imageView_logo;

    @FXML
    TableView<Jury> table;

    public JuryListController(User user) {
        this.user = user;
    }

    public JuryListController() {

    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("organizer.fxml", new OrganizerController(user));
    }
}
