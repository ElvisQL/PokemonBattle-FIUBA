package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.move.GameMove;
import org.fiuba.algoritmos3.model.move.GameMoveResult;
import org.fiuba.algoritmos3.model.move.errors.NoRemainingUsesError;

import java.io.IOException;
import java.util.List;

public interface GameAPI {

    void start();

    <T extends GameMove> GameMoveResult<String> play(T gameMove);

    void stop();

    Player createPlayer(String name);

    Player currentPlayer();

    List<Player> getPlayers();
}
