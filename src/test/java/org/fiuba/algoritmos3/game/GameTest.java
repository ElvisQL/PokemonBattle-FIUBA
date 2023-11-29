package org.fiuba.algoritmos3.game;

import com.github.underscore.U;
import org.fiuba.algoritmos3.factories.item.FakeItemFactory;
import org.fiuba.algoritmos3.factories.pokemon.FakePokemonFactory;
import org.fiuba.algoritmos3.factories.pokemon.skills.FakeAttackSkillFactory;
import org.fiuba.algoritmos3.model.Game;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.menu.MenuItem;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class GameTest {

    @Spy
    MockUI ui;

    List<Pokemon> playerOnePokemons;
    Pokemon playerOnePokemon;
    List<Pokemon> playerTwoPokemons;
    Pokemon playerTwoPokemon;

    @Test
    void start() throws IOException, InvalidSelectionException {
        HashMap<Integer, Item> itemHash = new HashMap<>();
        List<Item> itemList = new FakeItemFactory().createList(5);
        IntStream.range(0, itemList.size()).forEach((index) -> {
            itemHash.put(index, itemList.get(index));
        });

        HashMap<Integer, Pokemon> pokemonHash = new HashMap<>();

        Pokemon firstPokemon = new FakePokemonFactory().create(1);
        firstPokemon.setAttackSpeed(100);
        firstPokemon.getSkills().clear();
        firstPokemon.getSkills().add(
                new FakeAttackSkillFactory().create(1)
        );
        pokemonHash.put(1, firstPokemon);

        Pokemon secondPokemon = new FakePokemonFactory().create(2);
        secondPokemon.setAttackSpeed(99);
        secondPokemon.getSkills().clear();
        secondPokemon.getSkills().add(
                new FakeAttackSkillFactory().create(1)
        );
        pokemonHash.put(2, secondPokemon);

        new FakePokemonFactory().createList(4, 3).forEach(pokemon -> {
            pokemonHash.put(pokemon.getID(), pokemon);
        });

        Game game = new Game(pokemonSpecies, itemHash, pokemonHash);

        doReturn("player 1 name", "player 2 name").when(ui).askForText(contains("name"));

        // Choose first pokemon for player 1
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();

            playerOnePokemons = (List<Pokemon>) args[1];

            playerOnePokemon = U.find(playerOnePokemons, pokemon -> Objects.equals(pokemon.getID(), firstPokemon.getID())).get();
            return playerOnePokemon;
        }).when(ui).chooseOption(contains("player 1 name"), anyCollection(), anyList());

        // Choose first pokemon for player 2
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();

            playerTwoPokemons = (List<Pokemon>) args[1];

            playerOnePokemon = U.find(playerTwoPokemons, pokemon -> Objects.equals(pokemon.getID(), secondPokemon.getID())).get();
            return playerTwoPokemon;
        }).when(ui).chooseOption(contains("player 2 name"), anyCollection(), anyList());

        // Expect UI.chooseOption() calls
        doAnswer(new Answer() {
            private int invocationCount = 0;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                invocationCount++;
                Object[] args = invocation.getArguments();

                switch (invocationCount) {
                    case 1: {
                        // First Round (choose GameMove)
                        List<MenuItem<String, ?>> menuItems = (List<MenuItem<String, ?>>) args[1];

                        assertEquals(5, menuItems.size());
                        assertTrue(U.any(menuItems, menuItem -> menuItem.getItemText().contains("battlefield")));
                        assertTrue(U.any(menuItems, menuItem -> menuItem.getItemText().contains("Skill")));
                        assertTrue(U.any(menuItems, menuItem -> menuItem.getItemText().contains("Item")));
                        assertTrue(U.any(menuItems, menuItem -> menuItem.getItemText().contains("Pokemon")));
                        assertTrue(U.any(menuItems, menuItem -> menuItem.getItemText().contains("Surrender")));

                        // Choose "Use Skill"
                        return U.find(menuItems, menuItem -> menuItem.getItemText().contains("Skill")).get();
                    }
                    case 2: {
                        // Use Skill (choose a skill)
                        List<MenuItem<String, ?>> menuItems = (List<MenuItem<String, ?>>) args[1];

                        assertEquals(playerOnePokemon.getSkills().size(), menuItems.size());
                        assertTrue(U.any(menuItems, menuItem -> menuItem.getItemText().contains(playerOnePokemon.getSkills().get(0).getName())));
                        assertTrue(U.any(menuItems, menuItem -> menuItem.getItemText().contains(playerOnePokemon.getSkills().get(1).getName())));

                        // Choose "Use Skill"
//                        return U.find(menuItems, menuItem -> menuItem.getItemText().contains("Skill")).get();
                        return menuItems.get(0);
                    }
                }

                throw new Exception("Exceeded invocations");
            }
        }).when(ui).chooseOption(anyString(), anyCollection());

        game.start();

        Mockito.verify(ui).askForText(anyString());
    }
}