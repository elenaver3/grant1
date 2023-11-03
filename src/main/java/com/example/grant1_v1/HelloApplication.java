package com.example.grant1_v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage s;
    private static FXMLLoader loader;

    //
    @Override
    public void start(Stage stage) throws IOException {
        s = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        loader = fxmlLoader;
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        stage.setTitle("Добро пожаловать!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    public static FXMLLoader getLoader() {
        return loader;
    }

    public static void changeMainPage(String path) { //сменить текущую сцену
        //changeMainPage("hello-view.fxml",new HelloController());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path));
        try {
            Stage stage = s;
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            loader = fxmlLoader;
        } catch (IOException e) {
            System.out.println("Trouble with page creation");
            throw new RuntimeException(e);
        }
    }
    public static void changeMainPage(String path, Object controller) { //сменить текущую сцену
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path));
        try {
            Stage stage = s;
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            loader = fxmlLoader;
        } catch (IOException e) {
            System.out.println("Trouble with page creation");
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        launch();
    }
}