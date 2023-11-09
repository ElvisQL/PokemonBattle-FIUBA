package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.errors.InvalidDataException;
import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.game.Game;
import org.fiuba.algoritmos3.jsonManager.deserializer.ItemDeserializer;
import org.fiuba.algoritmos3.jsonManager.deserializer.PokemonDeserializer;
import org.fiuba.algoritmos3.models.item.Item;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

import java.io.IOException;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws IOException, InvalidSelectionException {
        UserInterface ui = new TerminalUI();
        HashMap<Integer, Item> items = null;
        HashMap<Integer, Pokemon> pokemons = null;

        try {
            items = new ItemDeserializer().getItems();
            pokemons = new PokemonDeserializer().getPokemon();
        } catch (InvalidDataException e) {
            ui.showMessage("Error in the game data: " + e.getMessage());
            System.exit(1);
        }

        Game game = new Game(ui, items, pokemons);
        game.start();
    }
}