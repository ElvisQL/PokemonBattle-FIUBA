package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.move.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.io.IOException;

public interface Skill {

    void use(Pokemon pokemon, Pokemon otherPokemon) throws BaseError;

    String getName() throws Exception;

}