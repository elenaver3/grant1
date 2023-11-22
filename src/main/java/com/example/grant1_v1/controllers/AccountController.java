package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AccountController {

    @FXML
    private Button button_back;

    @FXML
    private Button button_cancel;

    @FXML
    private Button button_save;

    @FXML
    private CheckBox checkBox_changePassword;

    @FXML
    private CheckBox checkBox_showPassword;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private ImageView imageView_photo;

    @FXML
    private Label label_idNumber;

    @FXML
    private PasswordField passwordField_password;

    @FXML
    private PasswordField passwordField_passwordRepeat;

    @FXML
    private RadioButton radioButton_female;

    @FXML
    private RadioButton radioButton_male;

    @FXML
    private TextField textField_birthday;

    @FXML
    private TextField textField_city;

    @FXML
    private TextField textField_email;

    @FXML
    private TextField textField_fio;

    @FXML
    private TextField textField_phone;



    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("hello-view.fxml");
    }
}
