package org.fiuba.algoritmos3.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.view.component.BaseButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.fiuba.algoritmos3.PokemonApp.mediaPlayer;

public class StartMenuController extends BaseController {
    public Button startButton;
    public Button exitButton;
    public BaseButton muteButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMusic("audio/mainmusic.wav");
        updateMuteButtonImages();
    }

    @FXML
    public void onClickExit(Event e) {
        stopMusic();
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void startGame(ActionEvent actionEvent) {
        BaseController controller = new PlayerSetupController();
        FXMLLoader fxmlLoader = new FXMLLoader(getResource("views/picker-wrapper.fxml"));
        controller.setPreviousController(this);
        fxmlLoader.setController(controller);

        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 768, 768);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        changeScene(actionEvent, scene);
    }

    @FXML
    public void onMuteButtonClick(MouseEvent _actionEvent) {
        mediaPlayer.setMute(!mediaPlayer.isMute());
        updateMuteButtonImages();
    }

    private void updateMuteButtonImages() {
        if (mediaPlayer.isMute()) {
            muteButton.setDefaultImageUrl("images/unmute.png");
            muteButton.setSelectedImageUrl("images/unmute-selected.png");
        } else {
            muteButton.setDefaultImageUrl("images/mute.png");
            muteButton.setSelectedImageUrl("images/mute-selected.png");
        }
    }
}