package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.DBConnect;
import com.example.grant1_v1.models.PasswordHashing;
import com.example.grant1_v1.models.Query;
import com.example.grant1_v1.models.Regex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterController {

    @FXML
    private Button button_back;

    @FXML
    private Button button_registration;

    @FXML
    private Button button_signIn;

    @FXML
    private ImageView imageView_logo;

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
    private TextField textField_country;

    @FXML
    private TextField textField_email;

    @FXML
    private TextField textField_fio;

    @FXML
    private TextField textField_phone;


    @FXML
    void confirmRegistrationSuccess(ActionEvent event) {

        boolean isName = textField_fio.getText().matches(Regex.getCheck_name());
        boolean isPassword = passwordField_password.getText().matches(Regex.getCheckPsw())
                & passwordField_passwordRepeat.getText().equals(passwordField_password.getText());
        boolean isEmail = textField_email.getText().matches(Regex.getCheckLog());
        boolean isPhone = textField_phone.getText().matches(Regex.checkPhone);
        boolean isBirthday = textField_birthday.getText().matches(Regex.getCheckBirthday());
        boolean isCity = textField_country.getText().matches(Regex.checkCity);
        boolean isMale = radioButton_male.isSelected();
        boolean isFemale = radioButton_female.isSelected();
        String male = "М";
        String female = "Ж";

        if (isName & isPassword & isEmail & isPhone & isBirthday & isCity & (isMale & !isFemale) || (!isMale & isFemale)) {
            //запрос к БД
            DBConnect.executePreparedInsert(Query.registerAuthorizationData, new ArrayList<String>(
                    Arrays.asList(textField_email.getText(), PasswordHashing.hashPassword(passwordField_password.getText()))));
            DBConnect.executePreparedInsert(Query.registerUser, new ArrayList<String>(
                    Arrays.asList(textField_email.getText(), textField_fio.getText())));
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
            if (!isBirthday) {
                s += "-День рождения ";
            }
            if (!isCity) {
                s += "-Город ";
            }
            if ((!isMale & !isFemale) || (isMale & isFemale)) {
                s += "-Пол ";
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
        Regex.addListenerFormatter(textField_birthday, Regex.getCheckBirthday());
    }
}
