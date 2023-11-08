package com.example.grant1_v1.controllers;

import javafx.embed.swing.SwingFXUtils;
import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.octo.captcha.service.image.ImageCaptchaService;

public class AuthController {

    private User profile;
    String captchaAnswer;
    DataSave saveAuth;
    private int count = 0;

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
    private TextField captchaField;

    @FXML
    private ImageView captchaImage;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private TextField log;

    @FXML
    private PasswordField psw;

    @FXML
    private Button register;

    @FXML
    private TextField pswViewed;

    @FXML
    void changePasswordFieldView(ActionEvent event) {
        if (checkBoxShowPassword.isSelected()) {
            psw.setVisible(false);
            pswViewed.setVisible(true);
            pswViewed.setText(psw.getText());
        } else {
            psw.setVisible(true);
            pswViewed.setVisible(false);
            psw.setText(pswViewed.getText());
        }
    }

    @FXML
    private Button refresh;

    @FXML
    void updateCaptcha(ActionEvent event) {
        refreshCaptcha();
    }

    @FXML
    public void initialize() {

        refreshCaptcha();
        //
        captchaField.setText(captchaAnswer);
        //

        Regex.addListenerFormatter(log, Regex.getCheckPositiveNumbers());
        saveAuth = new DataSave("auth");
        if (saveAuth.getValue("login", "") != null & saveAuth.getValue("password", "") != null) {
            MyAlert alert = new MyAlert("Хотите использовать сохраненные для входа данные?");
            if (alert.getAlert() == ButtonType.YES) {
                log.setText(saveAuth.getValue("login", ""));
                psw.setText(saveAuth.getValue("password", ""));
//                System.out.println(saveAuth.getValue("login", ""));
//                System.out.println(saveAuth.getValue("password", ""));
            }
        }
        pswViewed.setVisible(false);
    }

    @FXML
    void enter(ActionEvent event) {
        if (checkBoxShowPassword.isSelected()) {
            psw.setText(pswViewed.getText());
        }
        if (Regex.checkName(log.getText(), Regex.getCheckPositiveNumbers()) & Regex.checkName(psw.getText(), Regex.getCheckPsw()) & captchaField.getText().equals(captchaAnswer)) {
            try {
                ResultSet resultSet = DBConnect.executePreparedQuery(Query.checkAuth, new ArrayList<String>(Arrays.asList(log.getText(), PasswordHashing.hashPassword(psw.getText()))));
                if (resultSet.next()) {
                    if (resultSet.getString(1).equals("1")) {
                        resultSet = DBConnect.executePreparedQuery(Query.getRole, new ArrayList<String>(Arrays.asList(log.getText())));
                        if (checkBoxRemember.isSelected()) {
                            saveAuth.saveValue("login", log.getText());
                            saveAuth.saveValue("password", psw.getText());
                        }
                        if (resultSet.next()) {
                            profile = new User(log.getText());

                            if (resultSet.getString(1).equals("1")) {
                                HelloApplication.changeMainPage("organizer.fxml", new OrganizerController(profile));
                            } else {
                                if (resultSet.getString(1).equals("2")) {
                                    HelloApplication.changeMainPage("moderator.fxml", new ModeratorController(profile));
                                } else {
                                    if (resultSet.getString(1).equals("3")) {
                                        HelloApplication.changeMainPage("jury.fxml", new JuryController(profile));
                                    } else {
                                        HelloApplication.changeMainPage("participant.fxml", new ParticipantController(profile));
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
        } else {
            if (count > 3) {
                refreshCaptcha();
                MyAlert myAlert = new MyAlert("Данные неверны. Для повторного ввода подождите 10 секунд");
                myAlert.sleep();
                count = 0;
            } else {
                count++;
            }
        }

    }

    @FXML
    void register(ActionEvent event) {
        HelloApplication.changeMainPage("registration.fxml", new RegController());
    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("main.fxml", new MainController());
    }

    public void refreshCaptcha() {
        Object[] captchaAndResponse = GenerateCapcha.create();
        BufferedImage image = (BufferedImage) captchaAndResponse[1];
        Image img = SwingFXUtils.toFXImage((BufferedImage) (image), null);
        captchaImage.setImage(img);
        captchaAnswer = captchaAndResponse[0].toString();
    }
}
