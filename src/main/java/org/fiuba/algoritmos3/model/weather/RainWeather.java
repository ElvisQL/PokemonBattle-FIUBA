package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.util.List;


public class RainWeather implements BoostingWeather {

    @Override
    public String getName() {
        return "Rain";
    }

    @Override
    public List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Water, PokemonType.Plant);
    }

}
