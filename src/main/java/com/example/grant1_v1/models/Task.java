package com.example.grant1_v1.models;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Task {
    Text l;
    String text;
    String status;
    AnchorPane r;
    ListView<Task> currentList;


    public Task(String text, String status) {
        l = new Text(text);

        this.status = status;
        this.text = text+"";
        r = new AnchorPane();
        switch (status){
            case "TO DO":
                r.setStyle("-fx-background-color: pink;");
                break;
            case "IN ACTION":
                r.setStyle("-fx-background-color: yellow;");
                break;
            case "DONE":
                r.setStyle("-fx-background-color: green;");
                break;
        }

        r.setPrefHeight(50);
        r.setPrefWidth(100);
        r.setMaxHeight(50);
        r.setMaxWidth(100);
        r.getChildren().add(l);
        l.setLayoutX(10);
        l.setLayoutY(10);

    }

    public String getStatus() {
        return status;
    }

    public ListView<Task> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(ListView<Task> currentList) {
        this.currentList = currentList;
    }

    public Text getL() {
        return l;
    }

    public AnchorPane getR() {
        return r;
    }

    @Override
    public String toString() {
        return text;
    }
}
