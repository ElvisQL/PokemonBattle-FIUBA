package org.fiuba.algoritmos3.game.model.pokemon;

// Type Object (design pattern)
public class PokemonSpecies {

    private final String name;
    private final String history;
    private final PokemonType type;

    public PokemonSpecies(String name, String history, PokemonType type) {
        this.name = name;
        this.history = history;
        this.type = type;
    }
    // GETTER & SETTERS ---------------------------------------------------------------------------------------------------

    protected String getHistory() {
        return history;
    }

    protected PokemonType getType() {
        return type;
    }

    protected String getName() {
        return name;
    }


}