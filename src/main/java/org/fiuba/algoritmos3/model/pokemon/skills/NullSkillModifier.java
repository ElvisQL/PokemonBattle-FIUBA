package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.io.IOException;

public class NullSkillModifier extends SkillModifier {
    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon) throws NoRemainingUsesError, IOException {
        this.wrappee.use(pokemon, otherPokemon);
    }

    @Override
    public String getName() {
        return null;
    }
}