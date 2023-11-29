package org.fiuba.algoritmos3.jsonManager;

public enum JsonPath {

    POKEMON_SPECIES("src/resources/pokemon-species.json"),
    POKEMON("src/resources/pokemon.json"),
    SKILLS("src/resources/skills.json"),
    ITEMS("src/resources/items.json"),
    PLAYERS("data/players.json");

    private final String label;

    JsonPath(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
