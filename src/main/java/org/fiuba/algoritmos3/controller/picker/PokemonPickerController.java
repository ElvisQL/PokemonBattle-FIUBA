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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import org.fiuba.algoritmos3.PokemonApp;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.view.BaseButton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class PokemonPickerController extends PickerController<Pokemon> {
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
    private Text lifeText;

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
    private BaseButton okButton;
    @FXML
    private BaseButton backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Node node : pokemonChooserMenu.getChildren()) {
            if (node instanceof Pane pane) {
                pane.setOnMouseClicked(this::handleChoosePokemonMouseClick);
                pane.setOnMouseEntered(this::handleMouseEntered);
                pane.setOnMouseExited(this::handleMouseExited);
            }
        }
    }

    @Override
    public void setOptions(List<Pokemon> options) {
        super.setOptions(options);
        loadPokemons();
    }

    private void loadPokemons() {
        for (int i = 0; i < options.size(); i++) {
            Pokemon pokemon = options.get(i);
            Pane pane = (Pane) pokemonChooserMenu.getChildren().get(i);

            Text nameText = (Text) pane.lookup(".nameText");
            Text levelTextR = (Text) pane.lookup(".levelTextR");
            Text lifeTextT = (Text) pane.lookup(".lifeTextT");
            ProgressBar progressBar = (ProgressBar) pane.lookup(".progressBar");

            if (nameText != null && levelTextR != null && lifeTextT != null) {
                nameText.setText(pokemon.getName());
                levelTextR.setText(String.valueOf(pokemon.getLevel()));
                lifeTextT.setText(pokemon.getHealth() + "/" + pokemon.getMaxHealth());

                Double healthPercentage = (double) pokemon.getHealth() / (double) pokemon.getMaxHealth();
                progressBar.setProgress(healthPercentage);

                String typeImagePath = "images/" + pokemon.getType().toString().toLowerCase() + ".png";
                URL typeImageUrl = getResource(typeImagePath);

                if (typeImageUrl != null) {
                    Image typeImage = new Image(typeImageUrl.toExternalForm());
                    ImageView typeImageView = (ImageView) pane.lookup(".imageType");
                    typeImageView.setImage(typeImage);
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
        updateSelection();

        okButton.setDisable(false);
    }

    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        selection.setValue(options.get(selectedIndex));
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {

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
            if (nodo instanceof Pane) {
                Pane pane = (Pane) nodo;
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
        Polygon selector = (Polygon) viewPokemon.lookup("#selector");
        selector.setLayoutY(posY);
    }

    private Double calculatePosition() {
        selectedIndex = Math.max(0, Math.min(selectedIndex, pokemonChooserMenu.getChildren().size() - 1));

        Pane selectedPane = (Pane) pokemonChooserMenu.getChildren().get(selectedIndex);

        return selectedPane.getLayoutY();
    }

    private void updateLabelDetails(int index) {
        if (index >= 0 && index < pokemonChooserMenu.getChildren().size()) {
            Pane selectedPane = (Pane) pokemonChooserMenu.getChildren().get(index);

            Text pokemonNameText = (Text) selectedPane.lookup(".nameText");
            Text levelText = (Text) selectedPane.lookup(".levelTextR");
            Text lifeText = (Text) selectedPane.lookup(".lifeTextT");
            ProgressBar progressBar1 = (ProgressBar) selectedPane.lookup(".progressBar");
            //String attack = ((Text) selectedPane.lookup(".attackText")).getText();
            //String defense = ((Text) selectedPane.lookup(".defenseText")).getText();
            //String speed = ((Text) selectedPane.lookup(".speedText")).getText();

            if (pokemonNameText != null) {
                String pokemonName = pokemonNameText.getText();
                String level = levelText.getText();
                String life = lifeText.getText();
                Double progressPercentage = progressBar1.getProgress();

                viewProgressBar.setProgress(progressPercentage);
                pokemonNameText.setText(pokemonName);
                levelText.setText(level);
                lifeText.setText(life);

                updateImageView(pokemonName);
            }
            //attackText.setText("60");
            //defenseText.setText("50");
            //speedText.setText("30");
        }
    }

    private void updateImageView(String pokemonName) {
        String imagePath = "images/" + pokemonName.toLowerCase() + ".png";

        URL imageUrl = getResource(imagePath);

        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm());
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
