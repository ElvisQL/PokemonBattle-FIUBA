package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.util.List;


public class SandstormWeather implements BoostingWeather {

    @Override
    public String getName() {
        return "Sandstorm";
    }

    @Override
    public List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Ground, PokemonType.Rock);
    }

}
