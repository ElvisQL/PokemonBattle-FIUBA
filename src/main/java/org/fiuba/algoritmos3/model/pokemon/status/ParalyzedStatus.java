package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class ParalyzedStatus extends CanUseSkillStatus {

    @Override
    public String getName() {
        return "Paralyzed";
    }


    @Override
    protected boolean canUseSkill(Pokemon pokemon) {
        return Math.random() <= 0.5;
    }
}
