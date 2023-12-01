package org.fiuba.algoritmos3.model.event.listener;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.event.RoundOverEvent;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.util.List;

public class StatusListener implements GameEventListener<RoundOverEvent> {
    private final GameState gameState;

    public StatusListener(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void onEvent() {
        List<Player> players = List.of(
                gameState.getCurrentPlayer(),
                gameState.getCurrentPlayer().getOpponent()
        );

        players.forEach(player -> {
            List<Pokemon> pokemons = player.getPokemons();
            pokemons.forEach(pokemon -> {
                pokemon.getStatuses().forEach(status -> status.apply(pokemon));
            });
        });
    }
}
