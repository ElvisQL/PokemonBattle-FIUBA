package org.fiuba.algoritmos3.models;

import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PlayerTest {

    @Test
    void testSetName() {
        Player player = new Player("Ash", Collections.emptyList(), new ArrayList<>());

        player.setName("NewName");

        assertEquals("NewName", player.getName());
    }

    @Test
    void testSetCurrentPokemon() throws InvalidSelectionException {

        List<Pokemon> pokemons = Collections.singletonList(mock(Pokemon.class));
        Player player = new Player("Ash", pokemons, new ArrayList<>());

        player.setCurrentPokemon(pokemons.get(0));

        assertEquals(pokemons.get(0), player.getCurrentPokemon());
    }

    @Test
    void testSetCurrentPokemonInvalidSelection() {
        List<Pokemon> pokemons = Collections.singletonList(mock(Pokemon.class));
        Player player = new Player("Ash", pokemons, new ArrayList<>());

        assertThrows(InvalidSelectionException.class, () -> player.setCurrentPokemon(mock(Pokemon.class)));
    }

    @Test
    void testSurrender() {

        Player player = new Player("Ash", Collections.emptyList(), new ArrayList<>());

        player.surrender();

        assertTrue(player.getSurrendered());
    }

    @Test
    void testGetItems() {
        ArrayList<Item> items = new ArrayList<>(Collections.singletonList(mock(Item.class)));
        Player player = new Player("Ash", Collections.emptyList(), items);

        List<Item> result = player.getItems();

        assertEquals(items, result);
    }

}
