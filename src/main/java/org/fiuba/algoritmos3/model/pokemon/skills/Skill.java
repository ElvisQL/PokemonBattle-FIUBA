package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.io.IOException;

public interface Skill {

    void use(Pokemon pokemon, Pokemon otherPokemon) throws NoRemainingUsesError, IOException;

    String getName() throws Exception;

}