package org.fiuba.algoritmos3.view.chooseGameMove;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.fiuba.algoritmos3.PokemonApp;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.io.IOException;
import java.net.URL;

public class PokemonView extends HBox {
    @FXML
    private VBox statsBox;
    @FXML
    private Label pokemonName;
    @FXML
    private Label pokemonLevel;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private Label healthLabel;
    @FXML
    private Label maxHealthLabel;
    @FXML
    private ImageView pokemonTypeImageView;
    @FXML
    private ImageView pokemonImageView;

    @FXML
    private Label deadLabel;

    private final BooleanProperty flipped = new SimpleBooleanProperty(false);

    public static int instanceCount = 0;

    public PokemonView(Pokemon pokemon) {
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("views/chooseGameMove/pokemon-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);



        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        if (pokemon.isDead()) {
            statsBox.setDisable(true);
            deadLabel.setVisible(true);
        }

        pokemonName.setText(pokemon.getName());

        pokemonLevel.setText(pokemon.getLevel().toString());
        maxHealthLabel.setText(pokemon.getMaxHealth().toString());

        updateProgressBar(pokemon);
        if(instanceCount > 0 && instanceCount % 3 == 0 && instanceCount % 2 != 0){
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(150), pokemonImageView);
            translateTransition.setByY(60);
            translateTransition.setByX(50);
            translateTransition.play();
        }

        if (instanceCount > 0 && instanceCount % 2 == 0) {
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(650), pokemonImageView);

            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.5);

            fadeTransition.play();

            fadeTransition.setOnFinished(event -> {

                pokemonImageView.setOpacity(1.0);
            });
        }
        instanceCount++;

        URL pokemonTypeUrl = PokemonApp.class.getResource("images/pokemon-type/" + pokemon.getType().name().toLowerCase() + ".png");
        pokemonTypeImageView.setImage(new Image(pokemonTypeUrl.toExternalForm()));


        URL pokemonUrl = PokemonApp.class.getResource("images/pokemon/" + pokemon.getName().toLowerCase() + ".png");
        pokemonImageView.setImage(new Image(pokemonUrl.toExternalForm(), pokemonImageView.getFitWidth(), pokemonImageView.getFitHeight(), true, false));

    }

    private void updateProgressBar(Pokemon pokemon) {
        double mitad = (double) pokemon.getMaxHealth() / 2 / pokemon.getMaxHealth();

        healthBar.setProgress((double) pokemon.getHealth() / pokemon.getMaxHealth());
        healthLabel.setText(pokemon.getHealth().toString());
        if (healthBar.getProgress() < mitad) {
            healthBar.setStyle("-fx-accent: red;");
        } else if (healthBar.getProgress() <= 0) {
            healthBar.setStyle("-fx-accent: white;");
        } else {
            healthBar.setStyle("");
        }
    }


    public boolean isFlipped() {
        return flipped.get();
    }

    public BooleanProperty flippedProperty() {
        return flipped;
    }

    public void setFlipped(boolean newFlipped) {
        if (this.flipped.get() != newFlipped)
            flipView(newFlipped);
        this.flipped.set(newFlipped);
    }

    private void flipView(Boolean newFlipped) {
        if (newFlipped) {
            pokemonImageView.toFront();
            statsBox.toBack();
        } else {
            statsBox.toFront();
            pokemonImageView.toBack();
        }

        pokemonImageView.setScaleX(pokemonImageView.getScaleX() * -1);
    }
}
