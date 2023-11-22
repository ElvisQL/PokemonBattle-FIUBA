package org.fiuba.algoritmos3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerSetupController extends BaseController {
    @FXML
    private Button nextButton;
    @FXML
    private Label playerNameTitle;
    @FXML
    private TextField playerNameText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePlayerNameText();
    }


    @FXML
    public void onKeyTyped(KeyEvent keyEvent) {
        int nameLength = playerNameText.getText().length();
        boolean nameLengthWithinBounds = 0 < nameLength && nameLength < 50;
        nextButton.setDisable(!nameLengthWithinBounds);
    }

    @FXML
    private void onNextClick(MouseEvent event) throws InvalidSelectionException, IOException {
        String name = playerNameText.getText();

        Player currentPlayer = gameAPI.createPlayer(name);
        currentPlayer.setCurrentPokemon(currentPlayer.getPokemons().get(0)); // TODO hardcodeado

        if (gameAPI.getPlayers().size() >= 2) {
            // TODO esto tiene que pasar al choose pokemon
            gameAPI.start();
            changeScene(event, getResource("views/chooseGameMove/choose-game-move-view.fxml"));
        } else {
            PokemonChoiceController controller = (PokemonChoiceController) changeScene(event, getResource("views/pokemon-choice.fxml"));
            controller.setCurrentPlayer(currentPlayer);
            controller.setPreviousViewUrl(null);
            controller.setNextViewUrl(getResource("views/player-setup-view.fxml"));
        }
    }

    private void updatePlayerNameText() {
        playerNameTitle.setText("Jugador " + (gameAPI.getPlayers().size() + 1));
        playerNameText.clear();
    }
}
