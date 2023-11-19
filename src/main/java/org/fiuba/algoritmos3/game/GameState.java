package org.fiuba.algoritmos3.game;

import com.github.underscore.U;
import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.weather.NoneWeather;
import org.fiuba.algoritmos3.game.model.weather.Weather;

public class GameState {
    private final Player player1;
    private final Player player2;

    private Player currentPlayer;

    private Weather weather = new NoneWeather();


    public GameState(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.player1.setOpponent(this.player2);
        this.player2.setOpponent(this.player1);

        this.currentPlayer = getFirstPlayer(player1, player2);
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
        if (playerIsWinner(player1)) return player1;
        if (playerIsWinner(player2)) return player2;
        return null;
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

    private Player getFirstPlayer(Player player1, Player player2) {
        if (player1.getCurrentPokemon().getAttackSpeed() > player2.getCurrentPokemon().getAttackSpeed()) {
            return player1;
        } else {
            return player2;
        }
    }
}
