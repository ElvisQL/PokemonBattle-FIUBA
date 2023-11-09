package org.fiuba.algoritmos3.models.pokemon.skills;

public abstract class SkillModifier implements Skill {
    protected Skill wrappee;

    public SkillModifier wrap(Skill wrappee) {
        this.wrappee = wrappee;
        return this;
    }

    protected Class<? extends ConcreteSkill> getSkillType() {
        if (wrappee instanceof SkillModifier) {
            return ((SkillModifier) wrappee).getSkillType();
        }

        return (Class<? extends ConcreteSkill>) wrappee.getClass();
    }

    @Override
    public String getName() {
        return null;
    }
}