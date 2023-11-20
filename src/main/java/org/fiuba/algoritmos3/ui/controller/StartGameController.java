package org.fiuba.algoritmos3.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StartGameController extends BaseController {
    @FXML
    private Label playerNameTitle;
    @FXML
    private TextField playerNameText;
    private int playerCount = 1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePlayerNameText();
    }

    @FXML
    private void onEnterPressed(KeyEvent event) {
        if (!event.getCode().getName().equals("Enter")) {
            return;
        }

        String name = playerNameText.getText();

        gameAPI.createPlayer(name);

        playerCount++;
        if (playerCount > 2) {
            //hay que mostrar la siguiente view
            return;
        }

        updatePlayerNameText();
    }

    private void updatePlayerNameText() {
        playerNameTitle.setText("Por favor ingrese el nombre del jugador " + playerCount + ":");
        playerNameText.clear();
    }
}
