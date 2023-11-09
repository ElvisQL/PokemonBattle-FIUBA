package org.fiuba.algoritmos3.game.move;

import org.fiuba.algoritmos3.game.GameState;
import org.fiuba.algoritmos3.game.menu.operation.Operation;


public abstract class GameMove<Result, SubmenuResult> implements Operation<Result, SubmenuResult> {
    protected final GameState gameState;

    public GameMove(GameState gameState) {
        this.gameState = gameState;
    }

}
