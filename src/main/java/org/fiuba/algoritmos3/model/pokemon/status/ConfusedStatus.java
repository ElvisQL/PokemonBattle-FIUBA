package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class ConfusedStatus extends CanUseSkillStatus {
    private int turnsConfused = 3;

    @Override
    public String getName() {
        return "Confused";
    }

    @Override
    protected boolean canUseSkill(Pokemon pokemon) {
        turnsConfused--;

        if (Math.random() <= 1.0 / 3.0) {
            hitItself(pokemon);
            return false;
        }

        return true;
    }

    private void hitItself(Pokemon pokemon) {
        double currentHealth = pokemon.getHealth();
        double damage = pokemon.getMaxHealth() * 0.15;
        pokemon.setHealth((int) (currentHealth - damage));
    }
}
