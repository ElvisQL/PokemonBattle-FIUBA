package org.fiuba.algoritmos3.models.pokemon.status;

import org.fiuba.algoritmos3.Visitor;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

public class ConfusedStatus extends UseSkillStatus {
    int turnsConfused = 3;

    @Override
    public boolean canUseSkill(Pokemon pokemon) {
        turnsConfused--;

        if (Math.random() <= 1.0 / 3.0) {
            hitItself(pokemon);
            return false;
        }

        return true;
    }

    @Override
    public String getName() {
        return "Confused";
    }


    private void hitItself(Pokemon pokemon) {
        double currentHealth = pokemon.getHealth();
        double damage = pokemon.getMaxHealth() * 0.15;
        pokemon.setHealth((int) (currentHealth - damage));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
