package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.util.List;

public abstract class DamagingWeather implements Weather {
    @Override
    public void applyTo(GameState gameState) {
        List<Pokemon> pokemons = List.of(
                gameState.getCurrentPlayer().getCurrentPokemon(),
                gameState.getCurrentPlayer().getOpponent().getCurrentPokemon()
        );

        pokemons = pokemons.stream()
                .filter(pokemon -> !unaffectedPokemonTypes().contains(pokemon.getType()))
                .toList();


        pokemons.forEach(pokemon -> pokemon.setHealth((int) (pokemon.getHealth() - pokemon.getMaxHealth() * 0.03)));
    }

    protected List<PokemonType> unaffectedPokemonTypes() {
        return List.of();
    }
}
