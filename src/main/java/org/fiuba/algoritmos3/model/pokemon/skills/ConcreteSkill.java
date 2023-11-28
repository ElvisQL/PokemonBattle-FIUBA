package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public abstract class ConcreteSkill implements Skill {

    protected final String name;
    String description;

    protected ConcreteSkill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void use(Pokemon pokemon, Pokemon otherPokemon) throws BaseError {
        if (!pokemon.isDead()) {
            apply(pokemon, otherPokemon);
        }
    }

    public abstract void apply(Pokemon pokemon, Pokemon otherPokemon) throws BaseError;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return this.description;
    }
}