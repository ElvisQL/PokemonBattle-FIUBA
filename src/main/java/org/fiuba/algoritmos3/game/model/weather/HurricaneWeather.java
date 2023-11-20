package org.fiuba.algoritmos3.game.model.weather;

import org.fiuba.algoritmos3.game.model.pokemon.PokemonType;

import java.util.List;


public class HurricaneWeather extends BoostingWeather implements DamagingWeather {
    @Override
    protected List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Flying);
    }

    @Override
    public String getName() {
        return "Hurricane";
    }
}
