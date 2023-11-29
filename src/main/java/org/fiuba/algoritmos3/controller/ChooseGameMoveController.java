package org.fiuba.algoritmos3.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fiuba.algoritmos3.controller.gameMove.*;
import org.fiuba.algoritmos3.model.move.GameMoveResult;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.view.chooseGameMove.PokemonView;
import org.fiuba.algoritmos3.view.component.BaseButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseGameMoveController extends BaseController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox pokemonsSplitPane;
    @FXML
    private TextFlow gameMoveDescriptionTextFlow;
    @FXML
    private ImageView weatherBackground;

    @FXML
    private BaseButton useItemButton;
    @FXML
    private BaseButton useSkillButton;

    private GameMoveResult<String> gameMoveResult;

    public void setGameMoveResult(GameMoveResult<String> gameMoveResult) {
        this.gameMoveResult = gameMoveResult;
        updateGameMoveDescription();
    }

    private void updateGameMoveDescription() {
        if (gameMoveResult == null)
            return;

        gameMoveDescriptionTextFlow.getChildren().clear();

        String text = gameMoveResult.getResult();
        text += '\n' + "What will " + gameAPI.currentPlayer().getCurrentPokemon().getName() + " do?";
        Text msg = new Text(text);
        msg.getStyleClass().add("message-text-choose-game-move");

        gameMoveDescriptionTextFlow.getChildren().add(msg);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PokemonView opponentPokemonView = new PokemonView(gameAPI.currentPlayer().getOpponent().getCurrentPokemon());
        opponentPokemonView.setFlipped(true);
        pokemonsSplitPane.getChildren().add(opponentPokemonView);

        Pokemon currentPokemon = gameAPI.currentPlayer().getCurrentPokemon();
        PokemonView currentPokemonView = new PokemonView(currentPokemon);
        pokemonsSplitPane.getChildren().add(currentPokemonView);

        if (currentPokemon.isDead()) {
            useItemButton.setDisable(true);
            useSkillButton.setDisable(true);
        }

        Text msg = new Text("What will " + gameAPI.currentPlayer().getCurrentPokemon().getName() + " do?");
        msg.getStyleClass().add("message-text-choose-game-move");

        gameMoveDescriptionTextFlow.getChildren().add(msg);

        updateWeatherBackground();
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
    public void updateWeatherBackground() {
        String weatherName = gameAPI.getWeather().getName();
        String weatherImage = getResource("images/backgrounds/" + weatherName.toLowerCase() + ".png").toExternalForm();

        Image newImage = new Image(weatherImage, rootPane.getWidth(), rootPane.getHeight(), true, false);
        weatherBackground.setImage(newImage);
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
