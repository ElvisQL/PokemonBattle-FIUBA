package org.fiuba.algoritmos3.game;

import com.github.underscore.U;
import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.weather.NoneWeather;
import org.fiuba.algoritmos3.game.model.weather.Weather;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final int MAX_PLAYERS = 2;
    private final List<Player> players = new ArrayList<>();

    private Player currentPlayer;

    private Weather weather = new NoneWeather();

    public void addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS) {
            throw new IllegalArgumentException("The max amount of players is " + MAX_PLAYERS);
        }

        this.players.add(player);

        if (players.size() >= MAX_PLAYERS) {
            this.players.get(0).setOpponent(this.players.get(1));
            this.players.get(1).setOpponent(this.players.get(0));

            this.currentPlayer = getFirstPlayer();
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void swapPlayers() {
        currentPlayer = currentPlayer.getOpponent();
    }

    public Player getWinner() {
        return U.find(players, this::playerIsWinner).get();
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

    private Boolean playerIsWinner(Player player) {
        if (U.all(player.getOpponent().getPokemons(), Pokemon::isDead)) return true;
        return player.getOpponent().getSurrendered();
    }

    private Player getFirstPlayer() {
        return U.max(players, player -> player.getCurrentPokemon().getAttackSpeed());
    }
}
