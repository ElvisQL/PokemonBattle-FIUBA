package org.fiuba.algoritmos3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fiuba.algoritmos3.controller.StartMenuController;
import org.fiuba.algoritmos3.jsonManager.deserializer.ItemDeserializer;
import org.fiuba.algoritmos3.jsonManager.deserializer.PokemonDeserializer;
import org.fiuba.algoritmos3.jsonManager.deserializer.PokemonSpeciesDeserializer;
import org.fiuba.algoritmos3.model.Game;
import org.fiuba.algoritmos3.model.error.InvalidDataException;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;

import java.util.HashMap;
import java.util.List;


public class PokemonApp extends Application {
    StartMenuController startMenuController;
    private static GameAPI gameAPI;

    private static Stage mainStage;
    public static MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void init() throws Exception {
        HashMap<Integer, Item> items = null;
        HashMap<Integer, Pokemon> pokemons = null;
        List<PokemonSpecies> pokemonSpecies = null;

        try {
            items = new ItemDeserializer().getItems();
            pokemons = new PokemonDeserializer().getPokemons();
            pokemonSpecies = new PokemonSpeciesDeserializer().getPokemonSpecies();
        } catch (InvalidDataException e) {
            System.exit(1);
        }

        gameAPI = new Game(items, pokemons, pokemonSpecies);
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


    }

    public static void playMusic(Media music) {
        stopmusic();
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    public static void stopmusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
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