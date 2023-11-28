package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class AsleepStatus extends UseSkillStatus {
    private int elapsedRounds;

    public AsleepStatus() {
        this.elapsedRounds = 0;
    }

    @Override
    public boolean canUseSkill(Pokemon pokemon) {
        elapsedRounds++;
        double maxProbability = 0.25 + (elapsedRounds - 1) * 0.25;
        double probability = Math.min(maxProbability, 1.0);
        return Math.random() <= probability;
    }

    public int getElapsedRounds() {
        return elapsedRounds;
    }

    @Override
    public String getName() {
        return "Asleep";
    }

}
