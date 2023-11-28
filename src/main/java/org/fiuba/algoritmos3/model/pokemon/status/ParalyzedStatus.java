package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class ParalyzedStatus extends UseSkillStatus {

    @Override
    public boolean canUseSkill(Pokemon pokemon) {
        return !(Math.random() <= 0.5);
    }

    @Override
    public String getName() {
        return "Paralyzed";
    }

}
