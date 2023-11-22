package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public interface ApplyableStatus extends Status {
    void apply(Pokemon pokemon);

    @Override
    String getName();

}
