package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddEventController {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button buttonBoard;

    @FXML
    private Button buttonCsv;

    @FXML
    private Button buttonOk;

    @FXML
    private Button button_add;

    @FXML
    private ChoiceBox<?> choiceBox_jury1;

    @FXML
    private ChoiceBox<?> choiceBox_jury2;

    @FXML
    private ChoiceBox<?> choiceBox_jury3;

    @FXML
    private ChoiceBox<?> choiceBox_time1;

    @FXML
    private ChoiceBox<?> choiceBox_time2;

    @FXML
    private ChoiceBox<?> choiceBox_time3;

    @FXML
    private DatePicker datePicker_end;

    @FXML
    private DatePicker datePicker_start;

    @FXML
    private Button goBack;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private TextField textField_city;

    @FXML
    private TextField textField_direction;

    @FXML
    private TextField textField_event;

    @FXML
    private TextField textField_name1;

    @FXML
    private TextField textField_name2;

    @FXML
    private TextField textField_name3;

    @FXML
    void buttonBoard(ActionEvent event) {

    }

    @FXML
    void buttonCsv(ActionEvent event) {

    }

    @FXML
    void buttonOk(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        if (user.getMail().equals(""))
            HelloApplication.changeMainPage("event.fxml", new EventsController());
        else
            HelloApplication.changeMainPage("event.fxml", new EventsController(user));
    }

    public AddEventController(User user) {
        this.user = user;
    }

    public AddEventController() {
        this.user = new User();
    }
}
