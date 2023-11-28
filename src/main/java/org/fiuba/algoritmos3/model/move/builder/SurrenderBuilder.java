package org.fiuba.algoritmos3.model.move.builder;

import org.fiuba.algoritmos3.model.move.ChangePokemon;
import org.fiuba.algoritmos3.model.move.Surrender;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class SurrenderBuilder implements GameMoveBuilder<Surrender> {
    @Override
    public Surrender build() {
        return new Surrender();
    }
}
