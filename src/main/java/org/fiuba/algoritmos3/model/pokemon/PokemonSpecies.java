package org.fiuba.algoritmos3.model.pokemon;

import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;

import java.util.List;

// Type Object (design pattern)
public class PokemonSpecies {

    private final String name;
    private final String history;
    private final PokemonType type;

    private final List<ConcreteSkill> skills;

    public PokemonSpecies(String name, String history, PokemonType type, List<ConcreteSkill> skills) {
        this.name = name;
        this.history = history;
        this.type = type;
        this.skills = skills;
    }

    protected String getName() {
        return name;
    }

    protected String getHistory() {
        return history;
    }

    protected PokemonType getType() {
        return type;
    }

    public List<ConcreteSkill> getSkills() {
        return skills;
    }

}