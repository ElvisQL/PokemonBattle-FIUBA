package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.util.List;


public class RainWeather extends BoostingWeather {
    @Override
    protected List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Water, PokemonType.Plant);
    }

    @Override
    public String getName() {
        return "Rain";
    }

    @Override
    public void applyTo(GameState gameState) {

    }
}
