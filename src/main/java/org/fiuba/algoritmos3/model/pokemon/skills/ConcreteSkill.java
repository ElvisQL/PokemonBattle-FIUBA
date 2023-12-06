package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.status.AsleepStatus;

public abstract class ConcreteSkill implements Skill, Cloneable {

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

    public void setRemainingUses(Integer uses){
        this.remainingUses = uses;
    }

    @Override
    public ConcreteSkill clone() {
        try {
            ConcreteSkill clone = (ConcreteSkill) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            clone.remainingUses = Integer.valueOf(this.remainingUses);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}