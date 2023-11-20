package org.fiuba.algoritmos3.ui.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartMenuController extends BaseController {
    @FXML
    private MediaView mediaView;
    public MediaPlayer mediaPlayer;
    @FXML
    public ImageView muteButtonImage;
    @FXML
    public ImageView imageViewStart;
    @FXML
    public ImageView imageViewExit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String musicFile = Objects.requireNonNull(getClass().getResource("media/mainmusic.wav")).toExternalForm();

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
    public void onMuteButtonClick(MouseEvent _actionEvent) {
        mediaPlayer.setMute(!mediaPlayer.isMute());

        if (mediaPlayer.isMute()) {
            URL imageUrl = getClass().getResource("images/unmute.png");
            assert imageUrl != null;
            muteButtonImage.setImage(new Image(imageUrl.toExternalForm()));
        } else {
            URL imageUrl = getClass().getResource("images/mute.png");
            assert imageUrl != null;
            muteButtonImage.setImage(new Image(imageUrl.toExternalForm()));
        }

    }

    @FXML
    public void onMouseHover(Event e) {
        Pane sourcePane = (Pane) e.getSource();
        if (sourcePane.getChildren().get(0) == imageViewStart) {
            URL imageUrl = getClass().getResource("images/boton-menu-selected.png");
            assert imageUrl != null;
            imageViewStart.setImage(new Image(imageUrl.toExternalForm()));

        } else if (sourcePane.getChildren().get(0) == imageViewExit) {
            URL imageUrl = getClass().getResource("images/boton-menu-selected.png");
            assert imageUrl != null;
            imageViewExit.setImage(new Image(imageUrl.toExternalForm()));
        }
    }

    @FXML
    public void onMouseExited(Event e) {
        Pane sourcePane = (Pane) e.getSource();
        if (sourcePane.getChildren().get(0) == imageViewStart) {
            URL imageUrl = getClass().getResource("images/boton-menu.png");
            assert imageUrl != null;
            imageViewStart.setImage(new Image(imageUrl.toExternalForm()));
        } else if (sourcePane.getChildren().get(0) == imageViewExit) {
            URL imageUrl = getClass().getResource("images/boton-menu.png");
            assert imageUrl != null;
            imageViewExit.setImage(new Image(imageUrl.toExternalForm()));
        }
    }

    @FXML
    public void onClickExit(Event e) {
        mediaPlayer.stop();
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}