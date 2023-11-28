package org.fiuba.algoritmos3.controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.GameAPI;
import org.fiuba.algoritmos3.PokemonApp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public abstract class BaseController implements Initializable {

    protected final GameAPI gameAPI;

    protected BaseController previousController;

    protected BaseController() {
        gameAPI = PokemonApp.getGameAPI();
    }

    protected BaseController changeScene(Event e, URL url) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        return changeScene(stage, url);
    }
    protected void loadMusic(String musicFile) {
        Media media = new Media(getResource(musicFile).toExternalForm());
        PokemonApp.playMusic(media);
    }

    protected BaseController changeScene(Stage stage, URL url) {
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 768, 768);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(scene);

        BaseController nextController = fxmlLoader.getController();
        nextController.setPreviousController(this);
        return nextController;
    }

    protected void changeScene(Event e, Scene scene) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    protected URL getResource(String name) {
        return Objects.requireNonNull(PokemonApp.class.getResource(name));
    }

    public void setPreviousController(BaseController previousController) {
        this.previousController = previousController;
    }
    protected void stopMusic(){
        PokemonApp.stopmusic();
    }
}