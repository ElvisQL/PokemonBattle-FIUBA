package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class DeadStatus implements Status {
    @Override
    public String getName() {
        return "Dead";
    }

    @Override
    public void apply(Pokemon pokemon) {
        return;
    }


}
