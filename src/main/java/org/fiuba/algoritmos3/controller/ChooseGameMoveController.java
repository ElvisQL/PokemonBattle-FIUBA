package org.fiuba.algoritmos3.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fiuba.algoritmos3.controller.gameMove.*;
import org.fiuba.algoritmos3.view.chooseGameMove.PokemonView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChooseGameMoveController extends BaseController {
    public VBox pokemonsSplitPane;
    public TextFlow gameMoveDescriptionLabel;
    @FXML
    private ImageView backGroundWeather;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PokemonView opponentPokemonView = new PokemonView(gameAPI.currentPlayer().getOpponent().getCurrentPokemon());
        opponentPokemonView.setFlipped(true);
        pokemonsSplitPane.getChildren().add(opponentPokemonView);


        PokemonView currentPokemonView = new PokemonView(gameAPI.currentPlayer().getCurrentPokemon());
        pokemonsSplitPane.getChildren().add(currentPokemonView);

        Text msg = new Text("What will " + gameAPI.currentPlayer().getCurrentPokemon().getName() + " do?");
        msg.getStyleClass().add("message-text-choose-game-move");

        gameMoveDescriptionLabel.getChildren().add(msg);
        changeWeatherImage();
    }


    private void loadGameMoveController(Event e, GameMoveController<?, ?> controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getResource("views/picker-wrapper.fxml"));
        controller.setPreviousController(this);
        fxmlLoader.setController(controller);

        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 768, 768);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        changeScene(e, scene);
    }

    @FXML
    public void changeWeatherImage() {
        String weatherName = gameAPI.getWeather().getName();
        String weatherImage = getResource("images/backgrounds/" + weatherName.toLowerCase() + ".png").toExternalForm();


        try {
            Image newImage = new Image(weatherImage);
            backGroundWeather.setImage(newImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUseItemButtonAction(ActionEvent event) {
        loadGameMoveController(event, new UseItemController());
    }

    @FXML
    private void handleUseSkillButtonAction(ActionEvent event) {
        if (gameAPI.currentPlayer().getCurrentPokemon().isDead()) {
            handleChangePokemonButtonAction(event);
        } else {
            loadGameMoveController(event, new UseSkillController());
        }
    }

    @FXML
    private void handleChangePokemonButtonAction(ActionEvent event) {
        loadGameMoveController(event, new ChangePokemonController());
    }

    @FXML
    private void handleSurrenderButtonAction(ActionEvent event) {
        loadGameMoveController(event, new SurrenderController());
    }

}
