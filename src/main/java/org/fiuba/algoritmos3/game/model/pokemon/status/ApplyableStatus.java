package org.fiuba.algoritmos3.game.model.pokemon.status;

import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;

public interface ApplyableStatus extends Status {
    void apply(Pokemon pokemon);

    @Override
    String getName();

}
