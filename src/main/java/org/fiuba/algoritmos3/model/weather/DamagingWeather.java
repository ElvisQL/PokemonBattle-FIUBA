package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.util.List;

public interface DamagingWeather extends Weather {
    @Override
    default void endTurn(GameState gameState) {
        List<Pokemon> pokemons = List.of(
                gameState.getCurrentPlayer().getCurrentPokemon(),
                gameState.getCurrentPlayer().getOpponent().getCurrentPokemon()
        );

        if (this instanceof BoostingWeather boostingWeather) {
            pokemons = pokemons.stream()
                    .filter(pokemon -> !boostingWeather.boostedPokemonTypes().contains(pokemon.getType()))
                    .toList();
        }

        pokemons.forEach(pokemon -> pokemon.setHealth((int) (pokemon.getHealth() - pokemon.getMaxHealth() * 0.03)));
    }
}
