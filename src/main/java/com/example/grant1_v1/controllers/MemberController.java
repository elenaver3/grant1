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

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberController {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private TextField eventField;

    @FXML
    private Button goBack;

    @FXML
    private ImageView imageView_logo;

    @FXML
    private AnchorPane memberAnchor;

    @FXML
    TableView<Member> table;

    @FXML
    void eventField(ActionEvent event) {

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

        memberAnchor.getChildren().add(memberTable);
    }

    @FXML
    void goBack(ActionEvent event) {
        HelloApplication.changeMainPage("organizer.fxml", new OrganizerController(user));
    }

    public MemberController(User user) {
        this.user = user;
    }

    public MemberController() {

    }


}
