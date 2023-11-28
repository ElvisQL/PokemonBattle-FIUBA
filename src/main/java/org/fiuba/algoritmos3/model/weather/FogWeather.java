package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.util.List;


public class FogWeather extends BoostingWeather {
    @Override
    public List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Ghost, PokemonType.Psychic);
    }

    @Override
    public String getName() {
        return "Fog";
    }

    @Override
    public void applyTo(GameState gameState) {

    }
}
