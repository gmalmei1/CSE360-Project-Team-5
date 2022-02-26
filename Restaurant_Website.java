package com.restaurant;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.StackPane;

public class Restaurant_Website extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            double width = 1000, height = 750;
            String website_title = "www.fivenights@fortnite.com";

            HomePage gui = new HomePage();

            StackPane rootPane = new StackPane();
            rootPane.getChildren().add(gui);

            Scene scene = new Scene(rootPane, width, height);

            primaryStage.setTitle(website_title);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}