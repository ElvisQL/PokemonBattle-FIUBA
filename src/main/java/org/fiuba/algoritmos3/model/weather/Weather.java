package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;

public interface Weather {
    String getName();
    void applyTo(GameState gameState);
}
