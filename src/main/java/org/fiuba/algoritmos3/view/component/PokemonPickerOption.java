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

    private final SimpleObjectProperty<Pokemon> pokemon = new SimpleObjectProperty<>();

    private final BooleanProperty selected = new SimpleBooleanProperty();

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
}
