package org.fiuba.algoritmos3.ui.controller;

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

    BaseController() {
        gameAPI = PokemonApp.getGameAPI();
    }

    protected void changeScene(Event e, URL url) throws IOException {
        FXMLLoader startMenuFXML = new FXMLLoader(url);
        Scene scene = new Scene(startMenuFXML.load(), 768, 768);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    protected URL getResource(String name) {
        return PokemonApp.class.getResource(name);
    }
}
