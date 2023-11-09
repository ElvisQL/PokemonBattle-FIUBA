package org.fiuba.algoritmos3.game.move;

import org.fiuba.algoritmos3.UIDisplayable;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.game.Battlefield;
import org.fiuba.algoritmos3.game.GameState;
import org.fiuba.algoritmos3.game.menu.Menu;
import org.fiuba.algoritmos3.game.menu.operation.OperationResult;
import org.fiuba.algoritmos3.game.menu.operation.errors.BaseError;
import org.fiuba.algoritmos3.models.Player;
import org.fiuba.algoritmos3.models.weather.Weather;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ShowBattlefield extends GameMove<String, UIDisplayable> {
    public final static String label = "Display the battlefield";

    public ShowBattlefield(GameState gameState) {
        super(gameState);
    }

    @Override
    public OperationResult<String> run(UserInterface ui, OperationResult<UIDisplayable> submenuResult) throws IOException {
        Player player = gameState.getCurrentPlayer();
        Player otherPlayer = player.getOpponent();
        Weather weather = gameState.getWeather();

        new Battlefield(ui).displayDetailed(player, otherPlayer, weather);

        return new OperationResult<String>().Err(new BaseError(""));
    }

    @Override
    public @NotNull Menu<UIDisplayable> generateSubmenu() {
        return new Menu<>(null);
    }
}