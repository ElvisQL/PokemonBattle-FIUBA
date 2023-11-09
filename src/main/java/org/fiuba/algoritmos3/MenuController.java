package org.fiuba.algoritmos3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private MediaView mediaView;
    public MediaPlayer mediaPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String musicFile = getClass().getResource("media/mainmusic.wav").toExternalForm();

        Media media = new Media(musicFile);
        this.mediaPlayer = new MediaPlayer(media);
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.mediaView = new MediaView();
        this.mediaView.setMediaPlayer(mediaPlayer);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @FXML
    public void onMuteButtonClick(ActionEvent actionEvent) {
        mediaPlayer.setMute(!mediaPlayer.isMute());
    }


}