package org.fiuba.algoritmos3.view.battlefield;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class PokeballViewFactory {
    public PokeballView createPokeballView(Pokemon pokemon) {
        if (pokemon.isDead()) {
            return new WeakenedPokeball(pokemon);
        }
        return new StandardPokeball(pokemon);
    }
}
