package org.fiuba.algoritmos3.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.view.BaseButton;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartMenuController extends BaseController {
    public Button startButton;
    public Button exitButton;
    public BaseButton muteButton;
    @FXML
    private MediaView mediaView;
    public MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String musicFile = Objects.requireNonNull(getResource("audio/mainmusic.wav")).toExternalForm();

        Media media = new Media(musicFile);
        this.mediaPlayer = new MediaPlayer(media);
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.mediaPlayer.setMute(true);

        this.mediaView = new MediaView();
        this.mediaView.setMediaPlayer(mediaPlayer);

        updateMuteButtonImages();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @FXML
    public void onClickExit(Event e) {
        mediaPlayer.stop();
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, getResource("views/player-setup-view.fxml"));
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