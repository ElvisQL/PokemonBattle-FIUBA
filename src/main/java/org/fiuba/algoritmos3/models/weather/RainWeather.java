package org.fiuba.algoritmos3.models.weather;

import org.fiuba.algoritmos3.models.pokemon.PokemonType;

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
}
