package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;
import org.fiuba.algoritmos3.model.pokemon.skills.WithSkillModifiers;

import java.util.List;

public interface Weather extends WithSkillModifiers {
    String getName();

    void applyTo(GameState gameState);

    @Override
    default List<SkillModifier> getSkillModifiers() {
        return List.of();
    }
}
