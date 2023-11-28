package org.fiuba.algoritmos3.game;

import com.github.underscore.U;
import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Persistence;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.item.IncreaseDefenseItem;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.BuffSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.StatType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

class PersistenceTest {

    @Test
    @DisplayName("File from savePlayersInfo exists")
    void savePlayersInfo() {
        Pokemon charizard = mock(Pokemon.class);
        when(charizard.getID()).thenReturn(1);

        Pokemon squirtle = mock(Pokemon.class);
        when(squirtle.getID()).thenReturn(2);

        Player activePlayer = mock(Player.class);
        when(activePlayer.getName()).thenReturn("John");
        when(activePlayer.getPokemons()).thenReturn(List.of(charizard, squirtle));
        when(activePlayer.getItems()).thenReturn(List.of(new IncreaseDefenseItem(1, "testDefense", "mi super descripcion", 15)));

        Player jane = mock(Player.class);
        when(jane.getName()).thenReturn("Jane");
        when(jane.getPokemons()).thenReturn(List.of(squirtle));
        when(jane.getItems()).thenReturn(new ArrayList<>());

        GameState gameState = new GameState();
        gameState.addPlayer(activePlayer);
        gameState.addPlayer(jane);

        Persistence.savePlayersInfo(gameState);

        Assertions.assertTrue(Files.exists(new File("src/resources/players.json").toPath()));
        //TODO: Add assertions to check if the serialization was successful and saved as expected
    }

    @Test
    @DisplayName("File from Game Result exists")
    public void testGameOverStateGetsSaved() {
        Pokemon charizard = mock(Pokemon.class);
        when(charizard.getID()).thenReturn(1);

        Player jane = mock(Player.class);
        Pokemon squirtle = mock(Pokemon.class);
        when(squirtle.getID()).thenReturn(2);

        when(jane.getName()).thenReturn("Jane");
        when(jane.getPokemons()).thenReturn(List.of(squirtle));
        when(jane.getItems()).thenReturn(new ArrayList<>());

        Player activePlayer = mock(Player.class);
        when(activePlayer.getName()).thenReturn("John");
        when(activePlayer.getPokemons()).thenReturn(List.of(charizard, squirtle));
        when(activePlayer.getItems()).thenReturn(List.of(new IncreaseDefenseItem(1, "testDefense", "mi super descripcion", 15)));

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