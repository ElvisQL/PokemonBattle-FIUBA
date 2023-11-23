package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.Visitor;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.move.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public abstract class ConcreteSkill implements Skill {

    protected final String name;

    protected ConcreteSkill(String name) {
        this.name = name;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}