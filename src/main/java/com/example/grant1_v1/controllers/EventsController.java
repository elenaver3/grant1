package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

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
    TableView<MyEvent> table;
    @FXML
    private Rectangle tableSize;
    @FXML
    void goBack(ActionEvent event) {
            HelloApplication.changeMainPage("main.fxml", new MainController());
    }

    @FXML
    void buttonCreate(ActionEvent event) {
        HelloApplication.changeMainPage("add_event.fxml", new AddEventController(user));
    }

    @FXML
    void openEvent(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem()!=null) {
            HelloApplication.changeMainPage("event_information.fxml", new EventInfoController(table.getSelectionModel().getSelectedItem(),this));
            openEvent.setStyle("-fx-text-fill: red; ");
        }
    }

    @FXML
    public void initialize() {
        ResultSet resultSet = DBConnect.getDBConnect().executeQuery(Query.getEvents);
        ObservableList<MyEvent> items = FXCollections.observableArrayList();
        try {
            while (resultSet.next()) {
                items.add(new MyEvent(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Event list creation error");
            throw new RuntimeException(e);
        }
        FilteredList<MyEvent> filteredItems = new FilteredList<>(items, p->true);
        TableView<MyEvent> eventTable = new TableViewGenerator<MyEvent>(MyEvent.class,filteredItems,0,4).getTable();
        table = eventTable;
        TableColumn<MyEvent, ImageView> col = new TableColumn<>("logo");
        col.setCellValueFactory(new PropertyValueFactory<MyEvent, ImageView>(new ImageView(new Image())));
        table.setLayoutX(tableSize.getLayoutX());
        table.setLayoutY(tableSize.getLayoutY());
        table.setPrefHeight(tableSize.getHeight());
        table.setPrefWidth(tableSize.getWidth());
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        eventsAnchor.getChildren().add(eventTable);
        if(user.getAccessLevel()<2){
            eventsAnchor.getChildren().remove(buttonCreate);
        }
    }

    public EventsController(User user) {
        this.user = user;
    }

    public EventsController() {
        this.user = new User();
    }


}
