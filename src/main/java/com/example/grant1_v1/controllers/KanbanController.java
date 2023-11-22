package com.example.grant1_v1.controllers;

import com.example.grant1_v1.HelloApplication;
import com.example.grant1_v1.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class KanbanController {
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private ObservableList<Task> tasks;

    EventInfoController toPage;
    MyEvent event;

    Kanban k;

    @FXML
    private Button button_pdf;

    @FXML
    private ChoiceBox<?> choiceBox_event;

    @FXML
    private Button goBack;

    @FXML
    private Button PDFButton;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private TableView<?> tableView_activity;

    private TableView<Task> taskTable;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button CSVButton;

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("event_information.fxml", toPage);
    }

    @FXML
    public void initialize() {
        ResultSet resultSet = DBConnect.executePreparedModificationQueryWithResult(Query.getActivitiesKanban, event.getEventId()+"");
        ObservableList<ActivityKanban> items = FXCollections.observableArrayList();
        try {
            while (resultSet.next()) {
                items.add(new ActivityKanban(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Kanban list creation error");
            throw new RuntimeException(e);
        }

        taskTable = new TableView<>();

        ObservableList<Task> tasks = FXCollections.observableArrayList();
        for (int i = 0; i < items.size(); i++) {
            tasks.add(new Task(items.get(i).getActivityName(),items.get(i).getStatus()));
            taskTable.getItems().add(new Task(items.get(i).getActivityName(),items.get(i).getStatus()));
        }



        this.tasks = tasks;


//        taskTable.setItems(tasks);

        k = new Kanban(180, 80, tasks);
        pane.getChildren().add(k.getRoot());
    }

    public KanbanController(User user) {
        this.user = user;
    }

    public KanbanController() {
        this.user = new User();
    }

    public KanbanController(MyEvent event, EventInfoController toPage) {
        this.toPage = toPage;
        this.event = event;
    }


    @FXML
    void PDFButton(ActionEvent event) {
        PDFBuilder.createPDFAsList(tasks);
    }

    @FXML
    void CSVButton(ActionEvent event) {
        CSVBuilder.exportToCSV(taskTable, new Stage());
    }

}
