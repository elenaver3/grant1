package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.MyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class EventInfoController {
    EventsController toPage;
    MyEvent event;
    @FXML
    private TextArea descriptionEvent;

    @FXML
    private Button goBack;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private Label labelCity;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelOrganizer;

    @FXML
    private Label nameEvent;

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("event.fxml", toPage);
    }

    @FXML
    public void initialize() {
        labelDate.setText(labelDate.getText() + ": " + event.getStartDate());
        nameEvent.setText(nameEvent.getText() + ": " + event.getEventName());
    }

    public EventInfoController(MyEvent event, EventsController toPage) {
        this.toPage = toPage;
        this.event = event;
    }
}