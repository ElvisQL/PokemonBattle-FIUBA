package org.fiuba.algoritmos3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.controller.StartMenuController;
import org.fiuba.algoritmos3.jsonManager.deserializer.ItemDeserializer;
import org.fiuba.algoritmos3.jsonManager.deserializer.PokemonDeserializer;
import org.fiuba.algoritmos3.model.Game;
import org.fiuba.algoritmos3.model.error.InvalidDataException;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.util.HashMap;


public class PokemonApp extends Application {
    StartMenuController startMenuController;
    private static GameAPI gameAPI;

    private static Stage mainStage;

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

        gameAPI = new Game(items, pokemons);

        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setTitle("Pokemon Game");
        Image icono = new Image(getClass().getResourceAsStream("images/icono-pokemon.png"));
        mainStage.getIcons().add(icono);
        mainStage.setResizable(false);


        FXMLLoader startMenuFXML = new FXMLLoader(getClass().getResource("views/start-menu-view.fxml"));
        Scene startMenuScene = new Scene(startMenuFXML.load(), 768, 768);
        mainStage.setScene(startMenuScene);

        mainStage.show();

        startMenuController = startMenuFXML.getController();
        startMenuController.getMediaPlayer().play();
    }

    @Override
    public void stop() throws Exception {
        gameAPI.stop();
        super.stop();
    }

    public static GameAPI getGameAPI() {
        return gameAPI;
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}