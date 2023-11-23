package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class NullSkillModifier extends SkillModifier {
    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon) throws BaseError {
        this.wrappee.use(pokemon, otherPokemon);
    }

    @Override
    public String getName() {
        return null;
    }
}