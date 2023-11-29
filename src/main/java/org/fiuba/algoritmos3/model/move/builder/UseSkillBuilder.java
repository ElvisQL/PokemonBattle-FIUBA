package org.fiuba.algoritmos3.model.move.builder;

import org.fiuba.algoritmos3.model.move.UseSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;

public class UseSkillBuilder implements GameMoveBuilder<UseSkill> {

    private ConcreteSkill skill;

    public void setSkill(ConcreteSkill item) {
        this.skill = item;
    }

    @Override
    public UseSkill build() {
        return new UseSkill(skill);
    }

}
