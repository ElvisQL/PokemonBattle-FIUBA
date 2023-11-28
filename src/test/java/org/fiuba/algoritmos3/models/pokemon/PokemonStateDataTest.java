package org.fiuba.algoritmos3.models.pokemon;

import org.fiuba.algoritmos3.model.pokemon.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

class PokemonStateDataTest {

    @Test
    void buildFromPokemon() {
        Pokemon charizard = mock(Pokemon.class);
        PokemonBuilder pokemonBuilderMock = mock(PokemonBuilder.class);
        when(pokemonBuilderMock.build()).thenReturn(charizard);
        when(charizard.getID()).thenReturn(1);

        // Create a PokemonStateData instance from the Pokemon
        PokemonStateData stateData = new PokemonStateData().buildFromPokemon(charizard);


        // Verify that the PokemonStateData instance is correctly built
        Assertions.assertEquals(1, stateData.getId().intValue());
        Assertions.assertEquals(charizard.getStatusDescription(), stateData.getStatus());
        Assertions.assertEquals(charizard.getHealth(), stateData.getHealth().intValue());
    }
}