package org.fiuba.algoritmos3.model;

import com.github.underscore.U;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.weather.NoneWeather;
import org.fiuba.algoritmos3.model.weather.Weather;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    public List<Player> getPlayers() {
        return players;
    }

    private final List<Player> players = new ArrayList<>();

    private Player currentPlayer;

    private Weather weather = new NoneWeather();

    public void addPlayer(Player player) {
        this.players.add(player);
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
        return U.find(players, this::playerIsWinner).orNull();
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
}
