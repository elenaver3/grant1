package com.example.grant1_v1.models;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kanban {
    Task buffer = null;
    HBox root;
    ArrayList<ListView<Task>> rootList;
    ArrayList<String> columnNames;

    public HBox getRoot() {
        return root;
    }

    public Kanban(int x, int y) {
        rootList = new ArrayList<>();
        root = new HBox();
        root.setSpacing(10);
        root.setLayoutX(x);
        root.setLayoutY(y);
        root.setPrefWidth(400);
        root.setPrefHeight(400);
        columnNames = new ArrayList<>();
        addColumn("TO DO");
        addColumn("IN ACTION");
        addColumn("DONE");
//        Task t = new Task("ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt##", "TO DO");
//        Task t1 = new Task("E", "IN ACTION");
//        addTask(t);
//        addTask(t1);

        for (int i = 0; i < rootList.size(); i++) {
            setDragMotions(rootList.get(i));
        }


    }
    public Kanban(int x, int y, ObservableList<Task> list){
        rootList = new ArrayList<>();
        root = new HBox();
        root.setSpacing(10);
        root.setLayoutX(x);
        root.setLayoutY(y);
        root.setPrefWidth(400);
        root.setPrefHeight(400);
        columnNames = new ArrayList<>();
        addColumn("TO DO");
        addColumn("IN PROGRESS");
        addColumn("DONE");
        for (int i = 0; i < rootList.size(); i++) {
            setDragMotions(rootList.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            addTask(list.get(i));
        }

    }

    public void addColumn(String title) {
        columnNames.add(title);
        AnchorPane a = new AnchorPane();
        a.setPrefWidth(100);
        a.setPrefHeight(400);
        Label l = new Label(title);
        l.setLayoutX(5);
        l.setLayoutY(5);
        ListView<Task> col = new ListView();
        col.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> taskListView) {

                ListCell<Task> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Task item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            // Создание панели для отображения задачи
                            AnchorPane task = item.getR();
                            setGraphic(task);

                        }
                    }
                };

                return cell;
            }
        });
        col.setLayoutY(20);
        col.setLayoutX(0);
        a.getChildren().add(l);
        a.getChildren().add(col);
        root.getChildren().add(a);
        rootList.add(col);

    }

    public void addTask(Task task) {
        ListView<Task> listLink = null;
        switch (task.getStatus()) {
            case "IN ACTION":
                listLink = rootList.get(1);
                break;
            case "DONE":
                listLink = rootList.get(2);
                break;
            default:
                listLink = rootList.get(0);
                break;
        }
        if(task.getCurrentList()!=null) {
            task.getCurrentList().getItems().remove(task);
        }
        listLink.getItems().add(task);
        task.setCurrentList(listLink);

    }

    public void setDragMotions(ListView listView1) {
        listView1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    System.out.println(listView1.getSelectionModel().getSelectedItem());
                    Popup popup = new Popup();
                    popup.setAutoHide(true);
                    // Создаем контейнер для содержимого Popup
                    HBox contentBox = new HBox();
                    contentBox.getChildren().add(new Label("Пример Popup"));
                    contentBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

                    // Устанавливаем контент для Popup
                    popup.getContent().addAll(contentBox);

                    // Устанавливаем размеры Popup
                    popup.setWidth(400);
                    popup.setHeight(400);
                    //// СЮДА ВСТАВИТЬ ТЕКСТА
                    popup.show(root.getScene().getWindow());
                }
            }
        });

        listView1.setOnDragDetected(event -> {
            if (listView1.getSelectionModel().getSelectedItem() != null) {
                Dragboard dragboard = listView1.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                buffer = (Task) listView1.getSelectionModel().getSelectedItem();
                content.putString(listView1.getSelectionModel().getSelectedItem().toString() + "");
                dragboard.setContent(content);
                event.consume();
            }
        });

        listView1.setOnDragOver(event -> {
            if (event.getGestureSource() != listView1 && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        listView1.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasString()) {
                for (ListView<Task> t : rootList) {
                    t.getItems().remove(buffer);
                }
                listView1.getItems().add(new Task(dragboard.getString(), columnNames.get(rootList.indexOf(listView1))));
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }
}
