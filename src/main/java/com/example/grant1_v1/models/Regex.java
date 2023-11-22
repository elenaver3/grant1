package com.example.grant1_v1.models;

import javafx.scene.control.TextField;

public class Regex {
    private static final String checkPositiveNumbers = "0|([1-9]+[0-9]*)";
    private static final String check_name = "[[A-Z][a-z]+|[А-Я][а-я]+]{2,50}";
    private static final String check_log = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String check_psw = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    public static final String checkPhone ="^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";
    public static boolean checkName(String checkable, String mode){
        if(checkable.matches(mode)){
            return true;
        }
        return false;
    }
    public static String getCheckPositiveNumbers(){
        return checkPositiveNumbers;
    }
    public static String getCheck_name() {
        return check_name;
    }
    public static String getCheckLog() {
        return check_log;
    }
    public static String getCheckPsw() {
        return check_psw;
    }
    public static void addListenerFormatter(TextField t, String regex) {  //цветовая реакция на заполнение полей
        t.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches(regex)) {
                t.setStyle("-fx-text-fill: black; ");
            } else {
                t.setStyle("-fx-text-fill: red; ");
            }
        });
    }
}
