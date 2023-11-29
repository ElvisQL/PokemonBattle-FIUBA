package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public interface Skill {

    void use(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError;

    String getName() throws Exception;
}