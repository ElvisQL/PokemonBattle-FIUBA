package org.fiuba.algoritmos3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerSetupController extends BaseController {
    public AnchorPane rootPane;
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
    private void onNextClick(MouseEvent event) throws IOException, InvalidSelectionException {
        String name = playerNameText.getText();

        Player currentPlayer = gameAPI.createPlayer(name);
        currentPlayer.setCurrentPokemon(currentPlayer.getPokemons().get(0));
//        PokemonPickerController controller = (PokemonPickerController) changeScene(event, getResource("views/pokemon-picker.fxml"));
//        controller.setCurrentPlayer(currentPlayer);
//        controller.setPreviousViewUrl(null);
//
        if (gameAPI.getPlayers().size() >= 2)
            changeScene(event, getResource("views/chooseGameMove/choose-game-move-view.fxml"));
        else
            changeScene(event, getResource("views/player-setup-view.fxml"));
//            controller.setNextViewUrl(getResource("views/chooseGameMove/choose-game-move-view.fxml"));
//        else
//            controller.setNextViewUrl(getResource("views/player-setup-view.fxml"));
    }

    private void updatePlayerNameText() {
        playerNameTitle.setText("Jugador " + (gameAPI.getPlayers().size() + 1));
        playerNameText.clear();
    }
}
