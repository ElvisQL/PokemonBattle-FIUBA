package org.fiuba.algoritmos3.model.pokemon.status;


import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;
import org.fiuba.algoritmos3.model.pokemon.skills.WithSkillModifiers;

import java.util.List;

public interface Status extends WithSkillModifiers {

    String getName();

    @Override
    default List<SkillModifier> getSkillModifiers() {
        return List.of();
    }
}
