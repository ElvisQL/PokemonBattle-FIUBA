package org.fiuba.algoritmos3.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.fiuba.algoritmos3.model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class WinnerViewController extends BaseController {
    @FXML
    private Label winnerName;
    @FXML
    private ImageView buttonNewGame;

    private Player winner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setWinner(Player winner) {
        this.winner = winner;
        winnerName.setText(this.winner.getName() + " wins");
    }

    @FXML
    public void onMouseHover(Event e) {
        Pane sourcePane = (Pane) e.getSource();
        if (sourcePane.getChildren().get(0) == buttonNewGame) {
            URL imageUrl = getClass().getResource("images/boton-menu-selected.png");
            buttonNewGame.setImage(new Image(imageUrl.toExternalForm()));

        }
    }

    @FXML
    public void onMouseExit(Event e) {
        Pane sourcePane = (Pane) e.getSource();
        if (sourcePane.getChildren().get(0) == buttonNewGame) {
            URL imageUrl = getClass().getResource("images/boton-menu-selected.png");
            buttonNewGame.setImage(new Image(imageUrl.toExternalForm()));
        }
    }
}
