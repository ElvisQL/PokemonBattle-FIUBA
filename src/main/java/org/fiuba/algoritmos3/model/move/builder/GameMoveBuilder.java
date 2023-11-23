package org.fiuba.algoritmos3.model.move.builder;

import org.fiuba.algoritmos3.model.move.GameMove;

public interface GameMoveBuilder<T extends GameMove> {
    public abstract T build();
}
