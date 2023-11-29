package org.fiuba.algoritmos3.model.move.builder;

import org.fiuba.algoritmos3.model.move.UseSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;

public class UseSkillBuilder implements GameMoveBuilder<UseSkill> {

    private ConcreteSkill skill;

    @Override
    public UseSkill build() {
        return new UseSkill(skill);
    }

    public void setSkill(ConcreteSkill skill){
        this.skill = skill;
    }
}
