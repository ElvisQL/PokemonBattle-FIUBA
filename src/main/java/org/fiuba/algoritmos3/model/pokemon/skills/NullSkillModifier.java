package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class NullSkillModifier extends SkillModifier {
    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError {
        this.wrappee.use(pokemon, otherPokemon,state);
    }

    @Override
    public String getName() {
        return null;
    }
}