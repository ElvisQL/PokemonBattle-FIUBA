package org.fiuba.algoritmos3.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.fiuba.algoritmos3.game.model.Player;

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
    private void onPlayerNameClick(MouseEvent event) {
        String name = playerNameText.getText();

        currentPlayer = gameAPI.createPlayer(name);

        currentPlayer.getPokemons();
    }

    private void updatePlayerNameText() {
        playerNameTitle.setText("Jugador " + playerCount);
        playerNameText.clear();
    }

    @FXML
    public void onPokemonPickNextClick(MouseEvent mouseEvent) {
        // TODO pick pokemon
//        pokemonPicker
//        currentPlayer.setCurrentPokemon();

        playerCount++;
        if (playerCount >= 2) {
            //hay que mostrar la siguiente view
            return;
        }

        updatePlayerNameText();
    }
}
