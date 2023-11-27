package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.status.Status;


public class StatusSkill extends ConcreteSkill {
    private final Status status;


    public StatusSkill(String name, Status status, String description) {
        super(name, description);
        this.status = status;
    }

    @Override
    public void apply(Pokemon pokemon, Pokemon otherPokemon) {
        otherPokemon.addStatus(status);
    }
}
