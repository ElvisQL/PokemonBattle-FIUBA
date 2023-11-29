package org.fiuba.algoritmos3.models;

import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.PlayerData;
import org.fiuba.algoritmos3.model.item.IncreaseDefenseItem;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerDataTest {
    @Test
    void buildFromActivePlayer() {
        Pokemon charizard = mock(Pokemon.class);
        when(charizard.getID()).thenReturn(1);

        Pokemon squirtle = mock(Pokemon.class);
        when(squirtle.getID()).thenReturn(2);

        Player activePlayerMock = mock(Player.class);
        when(activePlayerMock.getName()).thenReturn("John");
        when(activePlayerMock.getItems()).thenReturn(
                List.of(new IncreaseDefenseItem(1, "testDefense", "mi super descripcion", 15))
        );
        when(activePlayerMock.getPokemons()).thenReturn(List.of(charizard, squirtle));


        PlayerData playerData = new PlayerData().buildFromActivePlayer(activePlayerMock);


        Assertions.assertEquals("John", playerData.getName());
        Assertions.assertEquals(1, playerData.getItems().size());
        Assertions.assertTrue(playerData.getItems().containsKey(1));
        Assertions.assertEquals(1, playerData.getItems().get(1));
        Assertions.assertEquals(2, playerData.getPokemons().size());
        Assertions.assertTrue(playerData.getPokemons().contains(1));
    }
}