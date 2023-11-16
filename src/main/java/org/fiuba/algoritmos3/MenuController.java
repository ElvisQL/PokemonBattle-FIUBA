package org.fiuba.algoritmos3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.fiuba.algoritmos3.errors.InvalidDataException;

import java.io.IOException;
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

    @FXML
    public void onClickOnStartGame(ActionEvent event) throws IOException, InvalidDataException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("startgame-view.fxml"));
        Parent root = loader.load();
        StartGameController startGameController = loader.getController();
        Scene currentScene = ((Node)event.getSource()).getScene();
        currentScene.setRoot(root);
        startGameController.initialize();
    }


}