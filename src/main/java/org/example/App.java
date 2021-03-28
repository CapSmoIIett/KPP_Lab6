package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Farm");
        stage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));

        Pane root =  (AnchorPane)loader.load();
        controller = loader.getController();

        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void stop() throws InterruptedException {
        controller.farm.closeFarm();                // remove threads
    }

    public static void main(String[] args) {
        launch();
    }

}