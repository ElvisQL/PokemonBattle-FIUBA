package org.fiuba.algoritmos3.controller.picker;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.view.component.BaseButton;
import org.fiuba.algoritmos3.view.component.PokemonPickerOption;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class PokemonPickerController extends PickerController<Pokemon> {

    @FXML
    private TextFlow descriptionBox;
    @FXML
    private Polygon selector;

    // Details Panel
    @FXML
    private Text nameText;
    @FXML
    private Text levelText;
    @FXML
    private Text maxHealth;
    @FXML
    private Text healthText;
    @FXML
    private Text attackText;
    @FXML
    private Text defenseText;
    @FXML
    private Text speedText;
    @FXML
    private ImageView pokemonImage;
    @FXML
    private ProgressBar viewProgressBar;

    @FXML
    private VBox pokemonOptionsParent;

    private MediaPlayer mediaPlayer;

    private Pokemon currentPokemon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Media media = new Media(getResource("audio/clickButton.mp3").toExternalForm());
        this.mediaPlayer = new MediaPlayer(media);

        setDescriptionBox("Choose wisely...");
    }

    @Override
    protected void updateView() {
        Player currentPlayer = this.gameAPI.currentPlayer();
        List<Pokemon> enemyPokemons;
        if (currentPlayer == null) {
            currentPokemon = getOptions().get(0);
            enemyPokemons = List.of();
        } else {
            currentPokemon = currentPlayer.getCurrentPokemon();
            enemyPokemons = currentPlayer.getOpponent().getPokemons();
        }

        pokemonOptionsParent.getChildren().clear();
        getOptions().forEach((pokemon) -> {
            PokemonPickerOption option = new PokemonPickerOption();
            option.setPokemon(pokemon);
            option.setEnemy(enemyPokemons.contains(pokemon));
            option.setOnMouseEntered((e) -> updateDetailsPanel(pokemon));
            option.setOnMouseExited((e) -> updateDetailsPanel(currentPokemon));
            option.setOnMouseClicked(this::handleChoosePokemonMouseClick);

            pokemonOptionsParent.getChildren().add(option);
        });
    }

    @FXML
    private void handleChoosePokemonMouseClick(MouseEvent event) {
        Node source = (Node) event.getSource();
        if (!(source instanceof PokemonPickerOption option)) return;

        currentPokemon = option.getPokemon();
        setDescriptionBox(currentPokemon.getHistory() + "Do you want to select " + currentPokemon.getName().toUpperCase() + "?");

        updateSelection(option);

        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();

        okButton.setDisable(false);
    }

    private void setDescriptionBox(String text) {
        descriptionBox.getChildren().clear();
        Text description = new Text(text);
        description.getStyleClass().add("description-pokemon-label");

        descriptionBox.getChildren().add(description);
    }

    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        selection.setValue(currentPokemon);
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        back.setValue(true);
    }


    private void updateSelection(PokemonPickerOption selectedOption) {
        for (Node node : pokemonOptionsParent.getChildren()) {
            if (node instanceof PokemonPickerOption option && option.isSelected()) {
                option.setSelected(false);
            }
        }

        selectedOption.setSelected(true);
        selector.setLayoutY(selectedOption.getLayoutY() + 120.0);
    }

    private void updateDetailsPanel(Pokemon pokemon) {
        nameText.setText(pokemon.getName());
        levelText.setText(String.valueOf(pokemon.getLevel()));
        healthText.setText(pokemon.getHealth().toString());
        maxHealth.setText(pokemon.getMaxHealth().toString());

        attackText.setText(pokemon.getAttackPoints().toString());
        defenseText.setText(pokemon.getDefencePoints().toString());
        speedText.setText(pokemon.getAttackSpeed().toString());

        Double healthPercentage = (double) pokemon.getHealth() / (double) pokemon.getMaxHealth();
        viewProgressBar.setProgress(healthPercentage);

        URL imageUrl = Objects.requireNonNull(getResource("images/pokemon/" + pokemon.getName().toLowerCase() + ".png"));
        Image image = new Image(imageUrl.toExternalForm(), pokemonImage.getFitWidth(), pokemonImage.getFitHeight(), true, false);
        pokemonImage.setImage(image);
    }
}
