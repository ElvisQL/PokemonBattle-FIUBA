package org.fiuba.algoritmos3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.view.BaseButton;

import java.net.URL;
import java.util.ResourceBundle;

public class WinnerViewController extends BaseController {
    public BaseButton newGameButton;
    @FXML
    private Label winnerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setWinner(Player winner) {
        winnerName.setText(winner.getName() + " wins");
    }
}
