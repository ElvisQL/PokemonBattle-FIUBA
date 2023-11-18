package org.fiuba.algoritmos3;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.errors.InvalidDataException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private MediaView mediaView;
    public MediaPlayer mediaPlayer;
    @FXML
    public ImageView muteButtonimage;
    @FXML
    public ImageView imageViewStart;
    @FXML
    public ImageView imageViewExit;



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
    public void onMuteButtonClick(MouseEvent actionEvent) {
        mediaPlayer.setMute(!mediaPlayer.isMute());

        if (mediaPlayer.isMute()){
            URL imageUrl = getClass().getResource("images/unmute.png");
            muteButtonimage.setImage(new Image(imageUrl.toExternalForm()));
        }
        else {
            URL imageUrl = getClass().getResource("images/mute.png");
            muteButtonimage.setImage(new Image(imageUrl.toExternalForm()));
        }

    }


    @FXML
    public void onMouseHover(Event e){
        Pane sourcePane = (Pane) e.getSource();
        if(sourcePane.getChildren().get(0) == imageViewStart){
            URL imageUrl = getClass().getResource("images/boton-menu-selected.png");
            imageViewStart.setImage(new Image(imageUrl.toExternalForm()));

        } else if (sourcePane.getChildren().get(0) == imageViewExit) {
            URL imageUrl = getClass().getResource("images/boton-menu-selected.png");
            imageViewExit.setImage(new Image(imageUrl.toExternalForm()));
        }
    }
    @FXML
    public void onMouseExited(Event e){
        Pane sourcePane = (Pane) e.getSource();
        if (sourcePane.getChildren().get(0) == imageViewStart) {
            URL imageUrl = getClass().getResource("images/boton-menu.png");
            imageViewStart.setImage(new Image(imageUrl.toExternalForm()));
        } else if (sourcePane.getChildren().get(0) == imageViewExit) {
            URL imageUrl = getClass().getResource("images/boton-menu.png");
            imageViewExit.setImage(new Image(imageUrl.toExternalForm()));
        }
    }
    @FXML
    public void onClickExit(Event e){
        mediaPlayer.stop();
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}