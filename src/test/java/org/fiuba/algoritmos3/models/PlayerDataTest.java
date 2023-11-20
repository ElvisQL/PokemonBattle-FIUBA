package org.fiuba.algoritmos3.models;

import com.github.underscore.U;
import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.PlayerData;
import org.fiuba.algoritmos3.game.model.item.IncreaseDefenseItem;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.game.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.game.model.pokemon.skills.BuffSkill;
import org.fiuba.algoritmos3.game.model.pokemon.skills.StatType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PlayerDataTest {
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

    Pokemon squirtle = new PokemonBuilder()
            .setID(2)
            .setRandomAttributes()
            .setSpecies(
                    new PokemonSpecies(
                            "Squirtle",
                            "Squirtle es un Pokémon de tipo Agua. Es uno de los Pokémon iniciales originales y es conocido por sus cañones de agua en su espalda.",
                            PokemonType.valueOf("Water")
                    ))
            .setSkills(
                    new ArrayList<>(List.of(
                            new AttackSkill("Pistola Agua", 40, U.random(10)),
                            new BuffSkill("Refugio", StatType.valueOf("DEFENSE"), 20)
                    )))
            .build();

    Player activePlayer = new Player(
            "John",
            List.of(charizard, squirtle),
            new ArrayList<>(List.of(new IncreaseDefenseItem(1, "testDefense", "mi super descripcion", 15)))
    );

    @Test
    void buildFromActivePlayer() {
        // Create a PlayerData instance from the active Player
        PlayerData playerData = new PlayerData().buildFromActivePlayer(activePlayer);

        // Verify that the PlayerData instance is correctly built
        Assertions.assertEquals("John", playerData.getName());
        Assertions.assertEquals(1, playerData.getItems().size());
        Assertions.assertTrue(playerData.getItems().containsKey(1));
        Assertions.assertEquals(1, playerData.getItems().get(1));
        Assertions.assertEquals(2, playerData.getPokemons().size());
        Assertions.assertTrue(playerData.getPokemons().contains(1));
    }
}