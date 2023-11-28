package org.fiuba.algoritmos3.model.move;

import org.fiuba.algoritmos3.model.GameState;

public abstract class GameMove {
    public abstract GameMoveResult<String> run(GameState gameState);
}
