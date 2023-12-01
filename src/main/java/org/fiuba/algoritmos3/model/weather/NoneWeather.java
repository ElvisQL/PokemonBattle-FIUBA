package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;

import java.util.List;

public class NoneWeather implements Weather {
    @Override
    public String getName() {
        return "Normal";
    }

    @Override
    public void applyTo(GameState gameState) {

    }
}
