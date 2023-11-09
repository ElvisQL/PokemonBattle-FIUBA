package org.fiuba.algoritmos3.models.pokemon;

public enum PokemonType {


    Water("Water"), Bug("Bug"), Dragon("Dragon"), Electric("Electric"),
    Ghost("Ghost"), Fire("Fire"), Ice("Ice"), Fighting("Fighting"),
    Normal("Normal"), Grass("Grass"), Psychic("Psychic"), Rock("Rock"),
    Ground("Ground"), Poison("Poison"), Flying("Flying"), Plant("Plant");

    private final String label;

    PokemonType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}