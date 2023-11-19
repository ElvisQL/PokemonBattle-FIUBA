package org.fiuba.algoritmos3.jsonManager.deserializer;

import org.fiuba.algoritmos3.game.error.InvalidDataException;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PokemonDeserializerTest {

    @Test
    void getPokemon() throws InvalidDataException {
        HashMap<Integer, Pokemon> pokemonDeserializer = new PokemonDeserializer().getPokemon();


        assertNotNull(pokemonDeserializer);
        Assertions.assertFalse(pokemonDeserializer.isEmpty());
        Assertions.assertEquals(pokemonDeserializer.get(1).getName(), "Squirtle");
    }
}