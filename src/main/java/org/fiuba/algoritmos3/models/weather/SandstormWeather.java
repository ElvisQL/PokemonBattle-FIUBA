package org.fiuba.algoritmos3.models.weather;

import org.fiuba.algoritmos3.models.pokemon.PokemonType;

import java.util.List;


public class SandstormWeather extends BoostingWeather {
    @Override
    protected List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Ground, PokemonType.Rock);
    }

    @Override
    public String getName() {
        return "Sandstorm";
    }
}
