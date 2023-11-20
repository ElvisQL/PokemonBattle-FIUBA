package org.fiuba.algoritmos3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.game.Game;
import org.fiuba.algoritmos3.game.error.InvalidDataException;
import org.fiuba.algoritmos3.game.model.item.Item;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.jsonManager.deserializer.ItemDeserializer;
import org.fiuba.algoritmos3.jsonManager.deserializer.PokemonDeserializer;
import org.fiuba.algoritmos3.ui.controller.StartMenuController;

import java.util.HashMap;


public class PokemonApp extends Application {

    StartMenuController startMenuController;

    GameAPI gameAPI;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        HashMap<Integer, Item> items = null;
        HashMap<Integer, Pokemon> pokemons = null;

        try {
            items = new ItemDeserializer().getItems();
            pokemons = new PokemonDeserializer().getPokemon();
        } catch (InvalidDataException e) {
            System.exit(1);
        }

        PokemonApp.launch();

        gameAPI = new Game(items, pokemons);
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("menu-view.fxml"));

        Parent root = fxmlLoader.load();
        startMenuController = fxmlLoader.getController();

        Scene scene = new Scene(root, 1024, 1024);
        stage.setTitle("Pokemon Game");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        startMenuController.getMediaPlayer().play();
    }

    @Override
    public void stop() {
        if (startMenuController != null) {
            startMenuController.getMediaPlayer().stop();
        }
    }
}