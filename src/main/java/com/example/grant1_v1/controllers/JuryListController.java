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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JuryListController {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private TextField filterEvent;

    @FXML
    private TextField filterF;

    @FXML
    private Button goBack;

    @FXML
    private Text labelCount;

    @FXML
    private ImageView imageView_logo;

    @FXML
    TableView<Jury> table;

    @FXML
    private AnchorPane juryMods;

    @FXML
    public void initialize() {
        ResultSet resultSet = DBConnect.getDBConnect().executeQuery(Query.getJuryies);
        ObservableList<Jury> items = FXCollections.observableArrayList();
        try {
            while (resultSet.next()) {
                items.add(new Jury(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Jury list creation error");
            throw new RuntimeException(e);
        }
        FilteredList<Jury> filteredItems = new FilteredList<>(items, p->true);
        TableView<Jury> juryTable = new TableViewGenerator<Jury>(Jury.class,filteredItems).getTable();
        table = juryTable;
        juryMods.getChildren().add(juryTable);
    }

    public JuryListController(User user) {
        this.user = user;
    }

    public JuryListController() {

    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("organizer.fxml", new OrganizerController(user));
    }
}
