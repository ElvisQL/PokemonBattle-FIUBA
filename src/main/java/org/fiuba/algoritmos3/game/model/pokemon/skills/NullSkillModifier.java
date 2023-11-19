package org.fiuba.algoritmos3.game.model.pokemon.skills;

import org.fiuba.algoritmos3.game.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;

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