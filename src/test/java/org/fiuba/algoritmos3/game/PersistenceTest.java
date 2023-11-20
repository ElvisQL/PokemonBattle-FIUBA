package org.fiuba.algoritmos3.game;

import com.github.underscore.U;
import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.item.IncreaseDefenseItem;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.game.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.game.model.pokemon.skills.BuffSkill;
import org.fiuba.algoritmos3.game.model.pokemon.skills.StatType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class PersistenceTest {

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
    Player jane = new Player(
            "Jane",
            List.of(squirtle),
            new ArrayList<>()
    );

    @Test
    @DisplayName("File from savePlayersInfo exists")
    void savePlayersInfo() {
        GameState gameState = new GameState(activePlayer, jane);
        Persistence.savePlayersInfo(gameState);

        Assertions.assertTrue(Files.exists(new File("src/resources/players.json").toPath()));
        // TODO: Add assertions to check if the serialization was successful and saved as expected
    }

    @Test
    @DisplayName("File from Game Result exists")
    public void testGameOverStateGetsSaved() {
        jane.setOpponent(activePlayer);
        GameState gameState = mock();
        doReturn(jane).when(gameState).getWinner();
        Persistence.saveGameResult(gameState);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = sdf.format(new Date()) + "-summary.json";

        Assertions.assertTrue(Files.exists(new File(fileName).toPath()));
        // TODO: Add assertions to check if the serialization was successful and saved as expected
    }

}