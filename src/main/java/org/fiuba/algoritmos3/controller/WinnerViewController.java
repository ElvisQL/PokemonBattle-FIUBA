package org.fiuba.algoritmos3.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.view.BaseButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinnerViewController extends BaseController {
    public BaseButton newGameButton;
    public BaseButton exitButton;
    @FXML
    private Label winnerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonAction();
    }

    private void setButtonAction() {
        gameAPI.clearPlayer();
        newGameButton.setOnAction(event -> {
            changeView("views/start-menu-view.fxml");
        });
    }

    public void onClickExit(Event e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setWinner(Player winner) {
        winnerName.setText(winner.getName() + " wins");
    }

    private void changeView(String viewName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getResource(viewName));
            Parent root = fxmlLoader.load();

            Scene currentScene = newGameButton.getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
