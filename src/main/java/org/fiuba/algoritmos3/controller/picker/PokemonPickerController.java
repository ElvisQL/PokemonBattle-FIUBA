package org.fiuba.algoritmos3.controller.picker;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.view.BaseButton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class PokemonPickerController extends PickerController<Pokemon> {

    @FXML
    private TextFlow descriptionBox;
    @FXML
    private VBox pokemonChooserMenu;
    private int selectedIndex;

    @FXML
    private Pane viewPokemon;
    @FXML
    private Text pokemonNameText;

    @FXML
    private Text levelText;
    @FXML
    private Text maxHealth;

    @FXML
    private Text currentLife;

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

    private MediaPlayer mediaPlayer;


    @FXML
    private BaseButton okButton;
    @FXML
    private BaseButton backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Media media = new Media(getResource("audio/clickButton.mp3").toExternalForm());
        this.mediaPlayer = new MediaPlayer(media);

        setDescriptionBox("Choose wisely");

        for (Node node : pokemonChooserMenu.getChildren()) {
            if (node instanceof Pane pane) {
                pane.setOnMouseClicked(this::handleChoosePokemonMouseClick);
                pane.setOnMouseEntered(this::handleMouseEntered);
                pane.setOnMouseExited(this::handleMouseExited);
            }
        }

    }

    @Override
    protected void updateView() {
        List<Pokemon> options = getOptions();
        for (int i = 0; i < options.size() && i < pokemonChooserMenu.getChildren().size(); i++) {
            Pokemon pokemon = options.get(i);
            Pane pane = (Pane) pokemonChooserMenu.getChildren().get(i);
            pane.setUserData(pokemon);
            Text nameTextMenu = (Text) pane.lookup(".nameTextMenu");
            Text levelTextMenu = (Text) pane.lookup(".levelTextMenu");
            Text lifeTextMenu = (Text) pane.lookup(".lifeTextMenu");
            ProgressBar progressBarMenu = (ProgressBar) pane.lookup(".progressBarMenu");

            if (nameTextMenu != null && levelTextMenu != null && lifeTextMenu != null) {

                nameTextMenu.setText(pokemon.getName());
                levelTextMenu.setText(String.valueOf(pokemon.getLevel()));
                lifeTextMenu.setText(pokemon.getHealth() + "/" + pokemon.getMaxHealth());

                Double healthPercentage = (double) pokemon.getHealth() / (double) pokemon.getMaxHealth();
                progressBarMenu.setProgress(healthPercentage);

                String typeImagePath = "images/pokemon-type/" + pokemon.getType().toString().toLowerCase() + ".png";
                URL typeImageUrl = getResource(typeImagePath);

                if (typeImageUrl != null) {
                    Image typeImage = new Image(typeImageUrl.toExternalForm());
                    ImageView imageType = (ImageView) pane.lookup(".imageType");
                    imageType.setImage(typeImage);
                }
            }
        }
    }

    @FXML
    private void handleChoosePokemonMouseClick(MouseEvent event) {
        Pane clickedPane = (Pane) event.getSource();
        if (!pokemonChooserMenu.getChildren().contains(clickedPane))
            return;

        selectedIndex = pokemonChooserMenu.getChildren().indexOf(clickedPane);
        Pokemon pokemon = (Pokemon) pokemonChooserMenu.getChildren().get(selectedIndex).getUserData();

        setDescriptionBox(pokemon.getHistory());
        updateSelection();

        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
        okButton.setDisable(false);
    }

    private void setDescriptionBox(String text) {
        descriptionBox.getChildren().clear();
        Text description = new Text(text);
        description.setFill(Color.WHITE);
        descriptionBox.getChildren().add(description);
    }

    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        selection.setValue(getOptions().get(selectedIndex));
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        back.setValue(true);
    }

    public void handleMouseEntered(MouseEvent event) {
        Node source = (Node) event.getSource();

        if (source instanceof Pane pane) {
            int index = pokemonChooserMenu.getChildren().indexOf(pane);
            Polygon triangle = (Polygon) pane.lookup("#triangle" + (pokemonChooserMenu.getChildren().indexOf(pane) + 1));
            triangle.setFill(Color.web("#2e6099"));

            updateLabelDetails(index);
        } else if (source instanceof Polygon) {
            Polygon triangle = (Polygon) source;
            triangle.setFill(Color.web("#2e6099"));

            String triangleId = triangle.getId();
            if (triangleId != null && triangleId.matches("triangle\\d+")) {
                int index = Integer.parseInt(triangleId.substring(8)) - 1;
                updateLabelDetails(index);
            }
        }
    }

    public void handleMouseExited(MouseEvent event) {
        Node source = (Node) event.getSource();

        if (source instanceof Pane pane) {
            int index = pokemonChooserMenu.getChildren().indexOf(pane);
            Polygon triangle = (Polygon) pane.lookup("#triangle" + (index + 1));
            if (triangle != null) {
                triangle.setFill(Color.web("#4a8ac6"));
            }
        } else if (source instanceof Polygon) {
            Polygon triangle = (Polygon) source;
            triangle.setFill(Color.web("#4a8ac6"));
        }
    }

    private void updateSelection() {
        for (Node nodo : pokemonChooserMenu.getChildren()) {
            if (nodo instanceof Pane pane) {
                Polygon triangle = (Polygon) pane.lookup("#triangle" + (pokemonChooserMenu.getChildren().indexOf(pane) + 1));
                triangle.setFill(Color.web("#4a8ac6"));
            }
        }

        Pane selectedPane = null;
        if (selectedIndex >= 0 && selectedIndex < pokemonChooserMenu.getChildren().size()) {
            selectedPane = (Pane) pokemonChooserMenu.getChildren().get(selectedIndex);
        }

        if (selectedPane != null) {
            Polygon selectedTriangle = (Polygon) selectedPane.lookup("#triangle" + (selectedIndex + 1));
            selectedTriangle.setFill(Color.web("#2e6099"));

            Double posY = calculatePosition();
            moveSelector(posY);
        }

    }

    private void moveSelector(Double posY) {
        Polygon selectorPokemon = (Polygon) viewPokemon.lookup("#selectorPokemon");
        posY = posY + 99.0;
        selectorPokemon.setLayoutY(posY);
    }

    private Double calculatePosition() {
        selectedIndex = Math.max(0, Math.min(selectedIndex, pokemonChooserMenu.getChildren().size() - 1));

        Pane selectedPane = (Pane) pokemonChooserMenu.getChildren().get(selectedIndex);

        return selectedPane.getLayoutY();
    }

    private void updateLabelDetails(int index) {
        List<Pokemon> options = getOptions();
        if (index >= 0 && index < options.size()) {
            Pokemon pokemon = options.get(index);
            String pokemonName = pokemon.getName();

            pokemonNameText.setText(pokemon.getName());
            levelText.setText(String.valueOf(pokemon.getLevel()));
            currentLife.setText(pokemon.getHealth().toString());
            maxHealth.setText(pokemon.getMaxHealth().toString());

            attackText.setText(pokemon.getAttackPoints().toString());
            defenseText.setText(pokemon.getDefencePoints().toString());
            speedText.setText(pokemon.getAttackSpeed().toString());

            Double healthPercentage = (double) pokemon.getHealth() / (double) pokemon.getMaxHealth();
            viewProgressBar.setProgress(healthPercentage);

            updateImageView(pokemonName);
        }
    }

    private void updateImageView(String pokemonName) {
        String imagePath = "images/pokemon/" + pokemonName.toLowerCase() + ".png";
        URL imageUrl = getResource(imagePath);

        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm(), pokemonImage.getFitWidth(), pokemonImage.getFitHeight(), true, false);
            pokemonImage.setImage(image);
        }
    }

    @FXML
    private void onMouseEntered(Event e) {
        Button button = (Button) e.getSource();
        if (button.getGraphic() instanceof ImageView buttonImage) {
            URL imageUrl = getResource("images/default-button-selected.png");
            assert imageUrl != null;
            buttonImage.setImage(new Image(imageUrl.toExternalForm()));
        }
    }

    @FXML
    private void onMouseExited(Event e) {
        Button button = (Button) e.getSource();
        if (button.getGraphic() instanceof ImageView buttonImage) {
            URL imageUrl = getResource("images/default-button.png");
            assert imageUrl != null;
            buttonImage.setImage(new Image(imageUrl.toExternalForm()));
        }
    }
}
