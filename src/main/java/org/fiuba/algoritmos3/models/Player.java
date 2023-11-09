package org.fiuba.algoritmos3.models;

import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.models.item.Item;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private final List<Pokemon> pokemons;
    private final ArrayList<Item> items;
    private Pokemon currentPokemon;
    private Player opponent;
    private Boolean surrendered = false;

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player(String name, List<Pokemon> pokemons, ArrayList<Item> items) {
        this.name = name;
        this.pokemons = pokemons;
        this.items = items;
        this.currentPokemon = this.pokemons.get(0);
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public Pokemon getCurrentPokemon() {
        return currentPokemon;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setCurrentPokemon(Pokemon chosenPokemon) throws InvalidSelectionException {
        if (!this.getPokemons().contains(chosenPokemon) || chosenPokemon.isDead()) {
            throw new InvalidSelectionException("Pokemon");
        }

        currentPokemon = chosenPokemon;
    }


    public void surrender() {
        surrendered = true;
    }

    public Boolean getSurrendered() {
        return surrendered;
    }
}