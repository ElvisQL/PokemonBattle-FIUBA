package org.fiuba.algoritmos3.models.pokemon;

import com.github.underscore.U;
import org.fiuba.algoritmos3.model.pokemon.*;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PokemonStateDataTest {
    Pokemon charizard = new PokemonBuilder()
            .setID(1)
            .setSpecies(
                    new PokemonSpecies(
                            "Charizard",
                            "Charizard es un Pokémon de tipo Fuego/Volador. Es la evolución final de Charmander y es conocido por su poderoso aliento de fuego y su apariencia similar a un dragón.",
                            PokemonType.valueOf("Fire")
                    ))
            .setSkills(
                    new ArrayList<>(List.of(
                            new AttackSkill("Lanzallamas", 90, U.random(10)),
                            new AttackSkill("Vuelo", 70, U.random(10))
                    ))
            )
            .setRandomAttributes()
            .build();

    @Test
    void buildFromPokemon() {
        // Create a PokemonStateData instance from the Pokemon
        PokemonStateData stateData = new PokemonStateData().buildFromPokemon(charizard);

        // Verify that the PokemonStateData instance is correctly built
        Assertions.assertEquals(1, stateData.getId().intValue());
        Assertions.assertEquals(charizard.getStatusDescription(), stateData.getStatus());
        Assertions.assertEquals(charizard.getHealth(), stateData.getHealth().intValue());
    }
}