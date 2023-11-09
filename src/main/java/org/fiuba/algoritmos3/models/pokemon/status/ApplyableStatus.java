package org.fiuba.algoritmos3.models.pokemon.status;

import org.fiuba.algoritmos3.models.pokemon.Pokemon;

public interface ApplyableStatus extends Status {
    void apply(Pokemon pokemon);

    @Override
    String getName();

}
