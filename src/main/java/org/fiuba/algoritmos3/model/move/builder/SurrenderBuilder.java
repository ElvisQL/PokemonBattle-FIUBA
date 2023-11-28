package org.fiuba.algoritmos3.model.move.builder;

import org.fiuba.algoritmos3.model.move.Surrender;

public class SurrenderBuilder implements GameMoveBuilder<Surrender> {
    @Override
    public Surrender build() {
        return new Surrender();
    }
}
