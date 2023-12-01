package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;

import java.util.List;


public class FogWeather implements BoostingWeather {

    @Override
    public String getName() {
        return "Fog";
    }

    @Override
    public List<PokemonType> boostedPokemonTypes() {
        return List.of(PokemonType.Ghost, PokemonType.Psychic);
    }

}
