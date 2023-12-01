package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.DamageBoosterModifier;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;

import java.util.List;

public interface BoostingWeather extends Weather {

    @Override
    default void applyTo(GameState gameState) {
    }

    @Override
    default List<SkillModifier> getSkillModifiers() {
        return List.of(new DamageBoosterModifier(boostedPokemonTypes()));
    }

    List<PokemonType> boostedPokemonTypes();

}
