package com.KahaniM.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static Stage firstPage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        firstPage = primaryStage;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void runAnotherApp(Class<? extends Application> anotherAppClass) throws Exception {
        Application app2 = anotherAppClass.newInstance();
        Stage anotherStage = new Stage();
        app2.start(anotherStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
