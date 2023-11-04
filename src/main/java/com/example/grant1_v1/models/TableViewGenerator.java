package com.example.grant1_v1.models;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TableViewGenerator<T> {
    TableView<T> table;
    public TableViewGenerator(Class<T> clazz, FilteredList<T> list){
        table = new TableView<>();
        for (Field f:clazz.getDeclaredFields()) {
            TableColumn<T,String> col= new TableColumn<>(f.getName());
            col.setCellValueFactory(new PropertyValueFactory<T,String>(f.getName()));
            table.getColumns().add(col); //как-то поправить потом названия столбцов

        }
        table.getItems().addAll(list);
        table.setLayoutX(200);
        table.setLayoutY(200);
        table.setPrefHeight(300);
        table.setPrefWidth(700);
    }

    public TableView<T> getTable() {
        return table;
    }
}
