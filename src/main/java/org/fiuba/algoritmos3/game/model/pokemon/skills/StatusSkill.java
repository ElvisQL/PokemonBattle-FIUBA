package org.fiuba.algoritmos3.game.model.pokemon.skills;

import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.status.Status;


public class StatusSkill extends ConcreteSkill {
    private final Status status;


    public StatusSkill(String name, Status status) {
        super(name);
        this.status = status;
    }

    @Override
    public void apply(Pokemon pokemon, Pokemon otherPokemon) {
        otherPokemon.addStatus(status);
    }
}
