package org.fiuba.algoritmos3.game.move;

import org.fiuba.algoritmos3.UIDisplayable;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.game.GameState;
import org.fiuba.algoritmos3.game.menu.Menu;
import org.fiuba.algoritmos3.game.menu.operation.OperationResult;
import org.fiuba.algoritmos3.game.menu.operation.errors.BaseError;
import org.fiuba.algoritmos3.models.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Surrender extends GameMove<String, UIDisplayable> {
    public final static String label = "Surrender";

    public Surrender(GameState gameState) {
        super(gameState);
    }

    @Override
    public OperationResult<String> run(UserInterface ui, OperationResult<UIDisplayable> submenuResult) throws IOException {
        Player player = gameState.getCurrentPlayer();

        if (!ui.askForConfirmation("Are you sure you want to surrender")) {
            return new OperationResult<String>().Err(new BaseError("The player chose not to surrender"));
        }

        player.surrender();
        return new OperationResult<String>().Ok("The player " + player.getName() + " surrendered");
    }

    @Override
    public @NotNull Menu<UIDisplayable> generateSubmenu() {
        return new Menu<>(null);
    }
}