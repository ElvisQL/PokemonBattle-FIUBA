package org.fiuba.algoritmos3.controller.messages;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fiuba.algoritmos3.controller.BaseController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MessageDisplayController extends BaseController {
    private static final Duration LETTER_ANIMATION_DURATION = Duration.millis(50);
    @FXML public HBox customView;
    public AnchorPane rootPane;
    private String text;
    @FXML
    private Label message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void handleOkButton(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        changeScene(stage, getResource("views/chooseGameMove/choose-game-move-view.fxml")); //TODO cambiar a diferentes ventanas?
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        // TODO
    }

    protected void showMessage(String text) {
        animateMessage(text);
    }

    // TODO message view?
    private void animateMessage(String str) {
        AtomicInteger i = new AtomicInteger();
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                LETTER_ANIMATION_DURATION,
                event -> {
                    if (i.get() > str.length()) {
                        timeline.stop();
                    } else {
                        message.setText(str.substring(0, i.get()));
                        i.getAndIncrement();
                    }
                });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


}



