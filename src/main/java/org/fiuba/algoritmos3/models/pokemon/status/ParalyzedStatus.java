package org.fiuba.algoritmos3.models.pokemon.status;

import org.fiuba.algoritmos3.Visitor;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

public class ParalyzedStatus extends UseSkillStatus {

    @Override
    public boolean canUseSkill(Pokemon pokemon) {
        return !(Math.random() <= 0.5);
    }

    @Override
    public String getName() {
        return "Paralyzed";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
