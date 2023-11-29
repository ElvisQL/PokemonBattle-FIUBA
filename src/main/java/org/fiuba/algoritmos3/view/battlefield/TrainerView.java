package org.fiuba.algoritmos3.view.battlefield;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.fiuba.algoritmos3.GameAPI;
import org.fiuba.algoritmos3.PokemonApp;
import org.fiuba.algoritmos3.controller.BattleMessages;
import org.fiuba.algoritmos3.controller.messages.MessageDisplayController;
import org.fiuba.algoritmos3.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TrainerView extends HBox {


    @FXML private ImageView oppositeTrainer;
    @FXML private ImageView activeTrainer;
    @FXML private GridPane actPokeballGrid;
    @FXML private GridPane oppPokeballGrid;
    @FXML private Label message;
    private String text;

    public TrainerView(Player active, Player opponent){
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("views/trainersBattlefield/trainer-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

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

        // Start the message animation

    }

    private void fillGrid(GridPane grid) {
        grid.setHgap(10);
        for (int col = 0; col < 6; col++) { // TODO analizar cada pokemon
            Image image = new Image("org/fiuba/algoritmos3/images/pokeball-type/standard.png"); // TODO cambiar por pokeball view factory

            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(30);
            grid.add(imageView, col, 0);
        }
    }




}
