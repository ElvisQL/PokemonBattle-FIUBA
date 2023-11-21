package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.game.model.Player;

public interface GameAPI {

    void start();

    void stop();

    Player createPlayer(String name);
}
