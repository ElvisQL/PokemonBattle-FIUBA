package org.fiuba.algoritmos3.game.model.weather;

import org.fiuba.algoritmos3.game.model.pokemon.PokemonType;

import java.util.List;


public class ThunderstormWeather extends BoostingWeather {
    @Override
    protected List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Electric);
    }

    @Override
    public String getName() {
        return "Thunderstorm";
    }
}
