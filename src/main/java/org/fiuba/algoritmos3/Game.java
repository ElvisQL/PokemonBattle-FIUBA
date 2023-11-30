package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.model.GameModel;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.move.GameMove;
import org.fiuba.algoritmos3.model.move.GameMoveResult;
import org.fiuba.algoritmos3.model.weather.Weather;

import java.util.List;

public class Game implements GameAPI {

    private final GameModel gameModel;

    public Game(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void start() {

        gameModel.startGame();
    }

    @Override
    public <T extends GameMove> GameMoveResult<String> play(T gameMove) {
        GameMoveResult<String> result = gameModel.playMove(gameMove);
        return result;
    }

    @Override
    public void stop() {
        gameModel.stopGame();

    }

    @Override
    public void clearPlayer() {
        gameModel.clearPlayers();
    }

    @Override
    public Player createPlayer(String playerName) {
        Player player = gameModel.createPlayer(playerName);
        return player;
    }

    @Override
    public Player currentPlayer() {
        return gameModel.getCurrentPlayer();
    }

    @Override
    public List<Player> getPlayers() {
        return gameModel.getPlayersList();
    }

    @Override
    public Player getWinner() {
        return gameModel.getWinnerPlayer();
    }

    @Override
    public Weather getWeather() {
        return gameModel.getCurrentWeather();
    }
}
