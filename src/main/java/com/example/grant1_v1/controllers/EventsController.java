package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.*;
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

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button buttonCreate;

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
//        if (user.getMail().equals(""))
            HelloApplication.changeMainPage("main.fxml", new MainController());
//        else
//            HelloApplication.changeMainPage("organizer.fxml", new OrganizerController(user));
    }

    @FXML
    void buttonCreate(ActionEvent event) {
        HelloApplication.changeMainPage("add_event.fxml", new AddEventController(user));
    }

    @FXML
    void openEvent(ActionEvent event) {
        HelloApplication.changeMainPage("event_information.fxml", new EventInfoController());
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

    public EventsController(User user) {
        this.user = user;
    }

    public EventsController() {
        this.user = new User();
    }


}
