package com.example.grant1_v1;

import com.example.grant1_v1.controllers.AuthController;
import com.example.grant1_v1.models.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
       welcomeText.setText("Welcome to JavaFX Application!");
        try {
            HelloApplication.changeMainPage("authorization.fxml", new AuthController());
            DBConnect.getDBConnect().executeQuery("SELECT * FROM `auth` WHERE 1");
        } catch (Exception e) {
            System.out.println("AuthCreationPageTrouble");
            throw new RuntimeException(e);
        }
    }
}