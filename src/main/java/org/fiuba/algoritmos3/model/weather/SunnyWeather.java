package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.util.List;

public class SunnyWeather extends BoostingWeather {
    @Override
    protected List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Fire);
    }

    @Override
    public String getName() {
        return "Sunny";
    }
}
