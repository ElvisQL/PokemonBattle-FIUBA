package org.fiuba.algoritmos3.models;

import com.github.underscore.U;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.PlayerData;
import org.fiuba.algoritmos3.model.item.IncreaseDefenseItem;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.BuffSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.StatType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerDataTest {
    @Test
    void buildFromActivePlayer() {
        Pokemon charizardMock = mock(Pokemon.class);
        when(charizardMock.getID()).thenReturn(1);

        Pokemon squirtleMock = mock(Pokemon.class);
        when(squirtleMock.getID()).thenReturn(2);

        // Mock de Player
        Player activePlayerMock = mock(Player.class);
        when(activePlayerMock.getName()).thenReturn("John");
        when(activePlayerMock.getItems()).thenReturn(
                List.of(new IncreaseDefenseItem(1, "testDefense", "mi super descripcion", 15))
        );
        when(activePlayerMock.getPokemons()).thenReturn(List.of(charizardMock, squirtleMock));

        // Create a PlayerData instance from the active Player
        PlayerData playerData = new PlayerData().buildFromActivePlayer(activePlayerMock);

        // Verify that the PlayerData instance is correctly built
        Assertions.assertEquals("John", playerData.getName());
        Assertions.assertEquals(1, playerData.getItems().size());
        Assertions.assertTrue(playerData.getItems().containsKey(1));
        Assertions.assertEquals(1, playerData.getItems().get(1));
        Assertions.assertEquals(2, playerData.getPokemons().size());
        Assertions.assertTrue(playerData.getPokemons().contains(1));
    }
}