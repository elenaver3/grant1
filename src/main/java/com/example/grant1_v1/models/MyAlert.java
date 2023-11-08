package com.example.grant1_v1.models;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MyAlert {
    private Alert alert;
    public MyAlert(String alertText){
        alert = new Alert(Alert.AlertType.CONFIRMATION, alertText, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

    }
    public ButtonType getAlert(){
        return alert.getResult();
    }
    public void sleep(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
