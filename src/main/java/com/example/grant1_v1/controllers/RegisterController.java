package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterController {

    @FXML
    private Button button_back;

    @FXML
    private Button button_ok;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private PasswordField passwordField_password;

    @FXML
    private PasswordField passwordField_passwordRepeat;

    @FXML
    private TextField textField_email;

    @FXML
    private TextField textField_fio;

    @FXML
    private TextField textField_phone;

    @FXML
    private ChoiceBox<String> choiceBox_sex;

    @FXML
    private ChoiceBox<String> choiceBox_role;

    @FXML
    private ChoiceBox<String> choiceBox_event;

    @FXML
    private CheckBox checkBox_event;

    @FXML
    private CheckBox checkBox_password;

    @FXML
    private TextField textField_direction;

    @FXML
    private TextField textField_idNumber;


    @FXML
    void confirmRegistrationSuccess(ActionEvent event) {

        boolean isName = textField_fio.getText().matches(Regex.getCheck_name());
        boolean isPassword = passwordField_password.getText().matches(Regex.getCheckPsw())
                & passwordField_passwordRepeat.getText().equals(passwordField_password.getText());
        boolean isEmail = textField_email.getText().matches(Regex.getCheckLog());
        boolean isPhone = textField_phone.getText().matches(Regex.checkPhone);

        if (isName & isPassword & isEmail & isPhone) {
            //запрос к БД
            DBConnect.executePreparedInsert(Query.registerUser, new ArrayList<String>(
                    Arrays.asList(textField_email.getText(), textField_fio.getText())));
            DBConnect.executePreparedInsert(Query.registerAuthorizationData, new ArrayList<String>(
                    Arrays.asList(textField_idNumber.getText(), PasswordHashing.hashPassword(passwordField_password.getText()))));

            MyAlert alert = new MyAlert("Регистрация прошла успешно!");
            HelloApplication.changeMainPage("auth.fxml", new AuthController());
        } else {
            String s = "Следующие данные неверны: ";
            if (!isName) {
                s += "-ФИО ";
            }
            if (!isPassword) {
                s += "-Пароль ";
            }
            if (!isPhone) {
                s += "-Номер телефона ";
            }
            if (!isEmail) {
                s += "-email ";
            }
            MyAlert alert = new MyAlert(s);
        }
    }

    @FXML
    void goToAuthorizationPage(ActionEvent event) {
        HelloApplication.changeMainPage("auth.fxml", new AuthController());
    }

    @FXML
    public void initialize() {
        Regex.addListenerFormatter(textField_email, Regex.getCheckLog());
        Regex.addListenerFormatter(textField_fio, Regex.getCheck_name());
        Regex.addListenerFormatter(passwordField_password, Regex.getCheckPsw());
        Regex.addListenerFormatter(textField_phone, Regex.checkPhone);
        choiceBox_sex.getItems().addAll("М", "Ж");
        choiceBox_role.getItems().add("");
        choiceBox_event.getItems().add("");
    }
}


