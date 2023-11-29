package org.fiuba.algoritmos3.view.component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.fiuba.algoritmos3.PokemonApp;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.io.IOException;
import java.net.URL;

public class PokemonPickerOption extends AnchorPane {
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private Polygon triangle;
    @FXML
    private Text nameText;
    @FXML
    private Text levelText;
    @FXML
    private Text lifeText;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ImageView pokemonTypeImage;

    // Colored elements
    @FXML
    private Rectangle background;
    @FXML
    private Rectangle largeAccent;
    @FXML
    private Rectangle smallAccent;

    private final SimpleObjectProperty<Pokemon> pokemon = new SimpleObjectProperty<>();

    private final BooleanProperty selected = new SimpleBooleanProperty();

    private final BooleanProperty enemy = new SimpleBooleanProperty();

    public PokemonPickerOption() {
        super();
        initialize();
    }

    private void initialize() {
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("views/components/pokemon-picker-option.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        parentAnchorPane.setOnMouseEntered((e) -> triangle.setFill(Color.web("#2e6099")));
        parentAnchorPane.setOnMouseEntered((e) -> triangle.getStyleClass().add("triangle-not-selected"));
        parentAnchorPane.setOnMouseClicked((e) -> setSelected(!selected.get()));
    }

    public Pokemon getPokemon() {
        return pokemon.getValue();
    }

    public Property<Pokemon> pokemonProperty() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon.setValue(pokemon);
        loadPokemon(pokemon);
    }

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);

        if (selected)
            triangle.getStyleClass().add("triangle-selected");
        else
            triangle.getStyleClass().add("triangle-not-selected");
    }

    public boolean isEnemy() {
        return enemy.get();
    }

    public BooleanProperty enemyProperty() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy.set(enemy);
        updateEnemyStyle();
    }

    private void loadPokemon(Pokemon pokemon) {
        nameText.setText(pokemon.getName());
        levelText.setText(String.valueOf(pokemon.getLevel()));
        lifeText.setText(pokemon.getHealth() + "/" + pokemon.getMaxHealth());

        healthBar.setProgress((double) pokemon.getHealth() / pokemon.getMaxHealth());

        // Get and set pokemon type icon
        String typeImagePath = "images/pokemon-type/" + pokemon.getType().toString().toLowerCase() + ".png";
        URL typeImageUrl = PokemonApp.class.getResource(typeImagePath);

        if (typeImageUrl != null) {
            Image typeImage = new Image(typeImageUrl.toExternalForm());
            pokemonTypeImage.setImage(typeImage);
        }
    }

    private void updateEnemyStyle() {
        if (enemy.get()) {
            background.setFill(Color.web("#de8785"));
            largeAccent.setFill(Color.web("#ff1f1f"));
            smallAccent.setFill(Color.web("#ff1f1f"));
            triangle.setFill(Color.web("#c44b4b"));
            triangle.setStroke(Color.web("#ff5854"));
        } else {
            background.setFill(Color.web("#83c4de"));
            largeAccent.setFill(Color.DODGERBLUE);
            smallAccent.setFill(Color.DODGERBLUE);
            triangle.setFill(Color.web("#4a8ac6"));
            triangle.setStroke(Color.web("#53a9ff"));
        }
    }
}
