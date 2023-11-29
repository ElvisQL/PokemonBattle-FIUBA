package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class BuffSkill extends ConcreteSkill {
    private final StatType statToModify;
    private final Integer amount;

    public BuffSkill(String name, StatType statToModify, Integer modifierValue, String description) {
        super(name, description);
        this.statToModify = statToModify;
        this.amount = modifierValue;
    }

    public String apply(Pokemon pokemon, Pokemon otherPokemon) {

        switch (statToModify) {
            case ATTACK -> {
                pokemon.setAttackPoints(pokemon.getAttackPoints() + amount);
            }
            case HEALTH -> {
                pokemon.setHealth(pokemon.getHealth() + amount);
            }
            case DEFENSE -> {
                pokemon.setDefencePoints(pokemon.getDefencePoints() + amount);
            }
            case SPEED -> {
                pokemon.setAttackSpeed(pokemon.getAttackSpeed() + amount);
            }
        }
        return (pokemon.getName() + " has modified his stat " + statToModify.name() + " for " + amount.toString());
    }

}