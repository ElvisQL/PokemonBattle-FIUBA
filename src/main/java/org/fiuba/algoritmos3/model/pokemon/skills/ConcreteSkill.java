package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.Visitor;
import org.fiuba.algoritmos3.model.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.io.IOException;

public abstract class ConcreteSkill implements Skill {

    protected final String name;

    protected ConcreteSkill(String name) {
        this.name = name;
    }

    public void use(Pokemon pokemon, Pokemon otherPokemon) throws NoRemainingUsesError, IOException {
        if (!pokemon.isDead()) {
            apply(pokemon, otherPokemon);
        }
    }

    public abstract void apply(Pokemon pokemon, Pokemon otherPokemon) throws IOException, NoRemainingUsesError;

    public String getName() {
        return name;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}