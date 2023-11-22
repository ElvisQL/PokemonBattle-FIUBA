package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.model.Player;

import java.util.List;

public interface GameAPI {

    void start();

    void stop();

    Player createPlayer(String name);

    Player currentPlayer();

    List<Player> getPlayers();
}
