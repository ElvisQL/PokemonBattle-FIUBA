package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.status.AsleepStatus;

public abstract class ConcreteSkill implements Skill {

    protected final String name;
    protected Integer remainingUses;
    String description;

    protected ConcreteSkill(String name, String description, Integer remainingUses) {
        this.name = name;
        this.description = description;
        this.remainingUses = remainingUses;
    }

    public Integer getRemainingUses() {
        return remainingUses;
    }

    public void use(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError {
        if (!pokemon.isDead()) {
            apply(pokemon, otherPokemon, state);
        }

    }

    public abstract void apply(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public Class<? extends ConcreteSkill> getSkillType() {
        return this.getClass();
    }
}