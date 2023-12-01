package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.util.List;


public class HurricaneWeather extends DamagingWeather implements BoostingWeather {

    @Override
    public String getName() {
        return "Hurricane";
    }

    @Override
    public List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Flying);
    }

    @Override
    protected List<PokemonType> unaffectedPokemonTypes() {
        return boostedPokemonTypes();
    }
}
