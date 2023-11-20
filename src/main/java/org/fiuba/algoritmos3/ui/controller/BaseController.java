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
import java.util.Optional;

public abstract class BaseController implements Initializable {
    protected GameAPI gameAPI;

    protected void changeScene(Event e, URL url) throws IOException {
        FXMLLoader startMenuFXML = new FXMLLoader(url);
        Scene scene = new Scene(startMenuFXML.load(), 1024, 1024);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    protected URL getResource(String name) {
        return PokemonApp.class.getResource(name);
    }
}
