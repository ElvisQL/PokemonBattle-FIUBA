package org.fiuba.algoritmos3.game.move;

import org.fiuba.algoritmos3.UiDisplayableVisitor;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.models.Player;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokemonChooser {
    public static Pokemon chooseFirstPokemon(UserInterface ui, Player currentPlayer) throws IOException {
        List<Pokemon> pokemonCollection = currentPlayer.getPokemons();

        UiDisplayableVisitor visitor = new UiDisplayableVisitor();

        List<String> pokemonOptions = new ArrayList<>();
        for (Pokemon pokemon : pokemonCollection) {
            pokemon.accept(visitor);
            pokemonOptions.add(visitor.getItemText());
        }

        return ui.chooseOption(currentPlayer.getName() + " choose your initial Pokemon", pokemonCollection, pokemonOptions);
    }
}
