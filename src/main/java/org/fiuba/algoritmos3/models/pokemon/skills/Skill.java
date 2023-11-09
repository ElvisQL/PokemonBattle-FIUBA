package org.fiuba.algoritmos3.models.pokemon.skills;

import org.fiuba.algoritmos3.game.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

import java.io.IOException;

public interface Skill {

    void use(Pokemon pokemon, Pokemon otherPokemon) throws NoRemainingUsesError, IOException;

    String getName() throws Exception;

}