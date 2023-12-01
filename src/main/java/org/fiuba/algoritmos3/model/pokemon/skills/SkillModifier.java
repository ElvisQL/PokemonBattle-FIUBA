package org.fiuba.algoritmos3.model.pokemon.skills;

public abstract class SkillModifier implements Skill {
    protected Skill wrappee;

    public SkillModifier wrap(Skill wrappee) {
        this.wrappee = wrappee;
        return this;
    }

    public Class<? extends ConcreteSkill> getSkillType() {
        return wrappee.getSkillType();
    }

    @Override
    public String getName() {
        return null;
    }
}