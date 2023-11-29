package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.move.GameMove;
import org.fiuba.algoritmos3.model.move.GameMoveResult;
import org.fiuba.algoritmos3.model.weather.Weather;

import java.util.List;

public interface GameAPI {

    void start();

    <T extends GameMove> GameMoveResult<String> play(T gameMove);

    void stop();

    void clearPlayer();

    Player createPlayer(String name);

    Player currentPlayer();

    List<Player> getPlayers();

    Player getWinner();

    Weather getWeather();
    GameMoveResult<String> getMoveMessage();
    void setMoveMessage(GameMoveResult<String> msg);
}
