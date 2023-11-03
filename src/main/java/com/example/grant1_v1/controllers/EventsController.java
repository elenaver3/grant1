package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.DBConnect;
import com.example.grant1_v1.models.Event;
import com.example.grant1_v1.models.Query;
import com.example.grant1_v1.models.TableViewGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventsController {

    @FXML
    private Button goBack;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private Button openEvent;

    @FXML
    private AnchorPane eventsAnchor;

    @FXML
    TableView<Event> table;

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("main.fxml", new MainController());
    }

    @FXML
    void openEvent(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        ResultSet resultSet = DBConnect.getDBConnect().executeQuery(Query.getEvents);
        ObservableList<Event> items = FXCollections.observableArrayList();
        try {
            while (resultSet.next()) {
                items.add(new Event(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Event list creation error");
            throw new RuntimeException(e);
        }
        FilteredList<Event> filteredItems = new FilteredList<>(items, p->true);
        TableView<Event> eventTable = new TableViewGenerator<Event>(Event.class,filteredItems).getTable();
        table = eventTable;
        eventsAnchor.getChildren().add(eventTable);
    }


}
