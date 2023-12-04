package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.weather.Weather;

public class WeatherSkill  extends ConcreteSkill{
    Weather weather;

    public WeatherSkill(String name, Weather weather, String description, Integer remainingUses) {
        super(name, description, remainingUses);
        this.weather = weather;
    }

    @Override
    public void apply(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError {
        if(remainingUses <= 0){
            state.setAdditionalMsg("no remaining uses\n");
            return;
        }
        state.setWeather(weather);
        weather.applyTo(state);
        state.setAdditionalMsg(pokemon.getName() + " changed weather to " + weather.getName());
        remainingUses--;
    }
}
