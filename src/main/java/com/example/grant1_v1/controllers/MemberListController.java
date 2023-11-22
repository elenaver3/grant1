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

public class MemberListController {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button goBack;

    @FXML
    private TextField filterEvent;

    @FXML
    private TextField filterFio;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private Text countLabel;

    @FXML
    private AnchorPane memberAnchor;

    @FXML
    TableView<Member> table;

    @FXML
    void filterEvent(ActionEvent event) {

    }

    @FXML
    void filterFio(ActionEvent event) {

    }


    @FXML
    public void initialize() {
        ResultSet resultSet = DBConnect.getDBConnect().executeQuery(Query.getMembers);
        ObservableList<Member> items = FXCollections.observableArrayList();
        try {
            while (resultSet.next()) {
                items.add(new Member(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Member list creation error");
            throw new RuntimeException(e);
        }
        FilteredList<Member> filteredItems = new FilteredList<>(items, p->true);
        TableView<Member> memberTable = new TableViewGenerator<Member>(Member.class,filteredItems).getTable();
        table = memberTable;
        table.setLayoutX(150);
        table.setLayoutY(200);
        table.setPrefHeight(200);
        table.setPrefWidth(750);

        TableFilterGenerator<Member> filter = new TableFilterGenerator<>(table, filteredItems);
        filter.addNewEqualsFilter(filterFio, "name");
        filter.setFiltersToTable();
//        TableFilterGenerator<Member> filter2 = new TableFilterGenerator<>(table, filteredItems);
//        filter2.addNewEqualsFilter(filterEvent, "activity");
//        filter2.setFiltersToTable();

        memberAnchor.getChildren().add(memberTable);

        resultSet = DBConnect.getDBConnect().executeQuery(Query.getMembersCount);
        try {
            resultSet.next();
            countLabel.setText(resultSet.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("organizer.fxml", new OrganizerController(user));
    }

    public MemberListController(User user) {
        this.user = user;
    }

    public MemberListController() {

    }


}
