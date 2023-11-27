package org.fiuba.algoritmos3.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fiuba.algoritmos3.PokemonApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class MessageDisplayController extends BaseController {

    private static final Duration LETTER_ANIMATION_DURATION = Duration.millis(50);
    public AnchorPane rootPane;
    @FXML
    private ImageView oppositeTrainer;
    @FXML
    private ImageView activeTrainer;
    @FXML
    private GridPane actPokeballGrid;
    @FXML
    private GridPane oppPokeballGrid;
    @FXML
    private Label message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load opposite trainer image
        URL oppositeTrainerUrl = PokemonApp.class.getResource("images/trainers/" + "gen3_boy_front" + ".png");
        if (oppositeTrainerUrl != null) {
            oppositeTrainer.setImage(new Image(oppositeTrainerUrl.toExternalForm()));
        }

        // Load active trainer image
        URL activeTrainerUrl = PokemonApp.class.getResource("images/trainers/" + "gen3_kanto_ash_back1" + ".png");
        if (activeTrainerUrl != null) {
            activeTrainer.setImage(new Image(activeTrainerUrl.toExternalForm()));
        }

        // Fill grids
        fillGrid(oppPokeballGrid);
        fillGrid(actPokeballGrid);
        // TODO check pokemons status and set pokeballs accordingly

        // Start the message animation
        animateMessage();

    }

    private void animateMessage() {
        String str = "Very long long long long message we gotta be able to change";

        AtomicInteger i = new AtomicInteger();
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                LETTER_ANIMATION_DURATION,
                event -> {
                    if (i.get() > str.length()) {
                        timeline.stop();
                        try {
                            sleep(1000);
                            onMessageCompleted(event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        message.setText(str.substring(0, i.get()));
                        i.getAndIncrement();
                    }
                });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


}

    private void onMessageCompleted(ActionEvent event) throws IOException {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        changeScene(stage, getResource("views/chooseGameMove/choose-game-move-view.fxml")); //TODO cambiar a diferentes ventanas?
    }


    private void fillGrid(GridPane grid) {

        for (int col = 0; col < 6; col++) { // TODO analizar cada pokemon
            Image image = new Image("org/fiuba/algoritmos3/images/pokeball-type/standard.png"); // TODO cambiar por pokeball view factory
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(30);// Set the width of the ImageView
            grid.add(imageView, col, 0);

        }
    }
}



