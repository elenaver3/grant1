package com.example.grant1_v1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class CSVBuilder {
    public static void exportToCSV(TableView<?> tableView, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            for (int i = 0; i < tableView.getItems().size(); i++) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int j = 0; j < tableView.getColumns().size(); j++) {
                    TableColumn<?, ?> column = tableView.getColumns().get(j);
                    if (column.getCellData(i) != null) {
                        row.add(column.getCellData(i).toString());
                    } else {
                        row.add("");
                    }
                }
                data.add(row);
            }

            try (PrintWriter writer = new PrintWriter(selectedFile)) {
                StringBuilder sb = new StringBuilder();
                for (TableColumn t : tableView.getColumns()) {
                    sb.append(t.getText()).append(",");
                }
                sb.append("\n");
                for (ObservableList<String> row : data) {
                    for (String item : row) {
                        sb.append(item).append(",");
                    }
                    sb.append("\n");
                }
                writer.write(sb.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

//        // Выбираем файл для сохранения
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Сохранить CSV файл");
//        File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());
//        if (file != null) {
//            try {
//                // Открываем FileWriter для сохранения данных в файл
//                FileWriter writer = new FileWriter(file);
//                // Получаем все строки данных из таблицы и сохраняем их в файл
//                for ( p : tableView) {
//                    writer.write(person.getName() + "," + person.getAge() + "\n");
//                }
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}