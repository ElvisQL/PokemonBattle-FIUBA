package org.fiuba.algoritmos3.model;

import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerData {

    private String name;

    private HashMap<Integer, Integer> items;

    public String getName() {
        return name;
    }

    public HashMap<Integer, Integer> getItems() {
        return items;
    }

    public ArrayList<Integer> getPokemons() {
        return pokemons;
    }

    private ArrayList<Integer> pokemons;

    public PlayerData() {
    }

    public PlayerData buildFromActivePlayer(Player player) {
        return new PlayerData()
                .setItems(player.getItems())
                .setName(player.getName())
                .setPokemon(player.getPokemons());
    }

    private PlayerData setItems(List<Item> items) {
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

    private PlayerData setName(String name) {
        this.name = name;
        return this;
    }

    private PlayerData setPokemon(List<Pokemon> pokemons) {
        this.pokemons = new ArrayList<>();

        for (Pokemon pokemon : pokemons) {
            this.pokemons.add(pokemon.getID());
        }

        return this;
    }

}
