package org.fiuba.algoritmos3.view.battlefield;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.fiuba.algoritmos3.PokemonApp;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.net.URL;

public abstract class PokeballView {
    protected Pokemon pokemon;
    protected ImageView pokeballImageView;

    public PokeballView(Pokemon pokemon, String type) {
        this.pokemon = pokemon;

        URL pokeballTypeUrl = PokemonApp.class.getResource("images/pokeball-type/" + type + ".png");
        pokeballImageView.setImage(new Image(pokeballTypeUrl.toExternalForm()));
    }


}
