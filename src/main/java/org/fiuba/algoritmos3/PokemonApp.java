package org.fiuba.algoritmos3;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.net.URL;


public class PokemonApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("menu-view.fxml"));
        MenuController menuController = fxmlLoader.getController();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 750, 700);
        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        menuController.getMediaPlayer().play();

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {

    }
}