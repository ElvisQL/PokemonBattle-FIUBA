package org.fiuba.algoritmos3.controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.GameAPI;
import org.fiuba.algoritmos3.PokemonApp;

import java.io.IOException;
import java.net.URL;

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

    protected BaseController changeScene(Stage stage, URL url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 768, 768);
        stage.setScene(scene);

        BaseController nextController = fxmlLoader.getController();
        nextController.setPreviousController(this);
        return nextController;
    }

    protected BaseController changeScene(Event e, Scene scene) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    protected URL getResource(String name) {
        return PokemonApp.class.getResource(name);
    }

    public void setPreviousController(BaseController previousController) {
        this.previousController = previousController;
    }
}
