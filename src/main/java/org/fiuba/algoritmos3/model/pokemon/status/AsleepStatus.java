package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class AsleepStatus extends CanUseSkillStatus {
    private int elapsedRounds = 0;

    @Override
    public String getName() {
        return "Asleep";
    }

    @Override
    public void apply(Pokemon pokemon) {
        return;
    }

    @Override
    public boolean canUseSkill(Pokemon pokemon) {
        elapsedRounds++;
        double maxProbability = 0.25 + (elapsedRounds - 1) * 0.25;
        double probability = Math.min(maxProbability, 1.0);
        boolean result = Math.random() <= probability;
        if (result){
            pokemon.removeStatus(this);
            elapsedRounds = 0;
        }
        return result;

    }


}
