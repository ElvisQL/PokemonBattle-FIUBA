package org.fiuba.algoritmos3.model.move.builder;

import org.fiuba.algoritmos3.model.move.ChangePokemon;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class ChangePokemonBuilder implements GameMoveBuilder<ChangePokemon> {

    private Pokemon newPokemon;

    public void setNewPokemon(Pokemon newPokemon) {
        this.newPokemon = newPokemon;
    }

    @Override
    public ChangePokemon build() {
        return new ChangePokemon(newPokemon);
    }
}
