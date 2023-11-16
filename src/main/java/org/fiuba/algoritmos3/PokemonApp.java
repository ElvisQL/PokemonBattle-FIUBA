package org.fiuba.algoritmos3;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;




public class PokemonApp extends Application {

    MenuController menuController;
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("menu-view.fxml"));
        System.out.println(getClass().getResource("menu-view.fxml"));
        Parent root = fxmlLoader.load();

        menuController = fxmlLoader.getController();

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
        if (menuController != null) {
            menuController.getMediaPlayer().stop();
        }
    }
}