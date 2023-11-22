package org.fiuba.algoritmos3.model.move;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.menu.operation.Operation;


public abstract class GameMove<Result, SubmenuResult> implements Operation<Result, SubmenuResult> {
    protected final GameState gameState;

    public GameMove(GameState gameState) {
        this.gameState = gameState;
    }

}
