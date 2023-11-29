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

    public String use(Pokemon pokemon, Pokemon otherPokemon) throws BaseError {
        if (!pokemon.isDead()) {
            return apply(pokemon, otherPokemon);
        }
        return (pokemon.getName() + "is dead");
    }

    public abstract String apply(Pokemon pokemon, Pokemon otherPokemon) throws BaseError;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return this.description;
    }
}