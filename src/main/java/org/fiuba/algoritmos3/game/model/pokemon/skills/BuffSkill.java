package org.fiuba.algoritmos3.game.model.pokemon.skills;

import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;

public class BuffSkill extends ConcreteSkill {
    private final StatType statToModify;
    private final Integer amount;

    public BuffSkill(String name, StatType statToModify, Integer modifierValue) {
        super(name);
        this.statToModify = statToModify;
        this.amount = modifierValue;
    }

    public void apply(Pokemon pokemon, Pokemon otherPokemon) {

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
    }

}