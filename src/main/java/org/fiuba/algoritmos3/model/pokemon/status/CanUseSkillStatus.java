package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.skills.CanUseSkillModifier;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;

import java.util.List;

public abstract class CanUseSkillStatus implements Status {


    @Override
    public final List<SkillModifier> getSkillModifiers() {
        return List.of(
                new CanUseSkillModifier((this::canUseSkill))
        );
    }

    protected abstract boolean canUseSkill(Pokemon pokemon);

}
