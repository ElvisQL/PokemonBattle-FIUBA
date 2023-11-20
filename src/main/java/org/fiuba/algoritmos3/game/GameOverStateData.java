package org.fiuba.algoritmos3.game;

import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.item.Item;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonStateData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameOverStateData {
    private String name;

    public String getName() {
        return name;
    }

    public Boolean getWinner() {
        return winner;
    }

    public HashMap<Integer, Integer> getItems() {
        return items;
    }

    public ArrayList<PokemonStateData> getPokemons() {
        return pokemons;
    }

    private Boolean winner;
    private HashMap<Integer, Integer> items;
    private ArrayList<PokemonStateData> pokemons;

    public GameOverStateData buildPlayerGameOverState(Player player) {

        return new GameOverStateData()
                .getPokemonStates(player.getPokemons())
                .setName(player.getName())
                .getItemData(player.getItems());
    }

    public GameOverStateData setAsWinner(Boolean win) {
        this.winner = win;
        return this;
    }

    private GameOverStateData setName(String name) {
        this.name = name;
        return this;
    }

    private GameOverStateData getPokemonStates(List<Pokemon> pokemons) {
        this.pokemons = new ArrayList<>();

        for (Pokemon pokemon : pokemons) {
            this.pokemons.add(new PokemonStateData().buildFromPokemon(pokemon));
        }
        return this;

    }

    private GameOverStateData getItemData(List<Item> items) {
        this.items = new HashMap<>();
        items.forEach(
                item -> {
                    this.items.put(
                            item.getId(),
                            this.items.getOrDefault(item.getId(), 0) + 1
                    );
                }
        );
        return this;
    }

}
