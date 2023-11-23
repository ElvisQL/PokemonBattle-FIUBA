package org.fiuba.algoritmos3.model.move;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.move.errors.NoRemainingUsesError;

import java.io.IOException;

public abstract class GameMove {
    public abstract GameMoveResult<String> run(GameState gameState);
}
