package org.fiuba.algoritmos3.model.move;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Player;

public class Surrender extends GameMove {
    public final static String label = "Surrender";

    @Override
    public GameMoveResult<String> run(GameState gameState) {
        Player player = gameState.getCurrentPlayer();
        player.surrender();

        return new GameMoveResult<String>().Ok("The player " + player.getName() + " surrendered");
    }
}