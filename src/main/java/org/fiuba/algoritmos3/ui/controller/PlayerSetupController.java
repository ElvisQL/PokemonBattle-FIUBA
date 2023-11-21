package org.fiuba.algoritmos3.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.fiuba.algoritmos3.game.error.InvalidSelectionException;
import org.fiuba.algoritmos3.game.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerSetupController extends BaseController {
    public HBox pokemonPicker;
    @FXML
    private Label playerNameTitle;
    @FXML
    private TextField playerNameText;
    private int playerCount = 1;

    private Player currentPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePlayerNameText();
    }

    @FXML
    private void onPlayerNameClick(MouseEvent event) throws InvalidSelectionException, IOException {
        String name = playerNameText.getText();

        currentPlayer = gameAPI.createPlayer(name);
        currentPlayer.setCurrentPokemon(currentPlayer.getPokemons().get(0)); // TODO hardcodeado

        playerCount++;
        if (playerCount >= 2) {
            changeScene(event, getResource("game-move-view.fxml"));
            return;
        }

        updatePlayerNameText();
    }

    private void updatePlayerNameText() {
        playerNameTitle.setText("Jugador " + playerCount);
        playerNameText.clear();
    }
}
