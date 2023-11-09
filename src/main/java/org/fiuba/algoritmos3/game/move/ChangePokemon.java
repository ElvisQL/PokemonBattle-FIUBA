package org.fiuba.algoritmos3.game.move;

import com.github.underscore.U;
import org.fiuba.algoritmos3.UiDisplayableVisitor;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.game.GameState;
import org.fiuba.algoritmos3.game.menu.Menu;
import org.fiuba.algoritmos3.game.menu.MenuItem;
import org.fiuba.algoritmos3.game.menu.operation.OperationResult;
import org.fiuba.algoritmos3.models.Player;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChangePokemon extends GameMove<String, Pokemon> {
    public final static String label = "Change Pokemon";

    public ChangePokemon(GameState gameState) {
        super(gameState);
    }


    @Override
    public OperationResult<String> run(UserInterface ui, OperationResult<Pokemon> pokemonResult) throws InvalidSelectionException {

        if (pokemonResult.isErr()) {
            return new OperationResult<String>().Err(pokemonResult.getError());
        }
        Player player = gameState.getCurrentPlayer();
        UiDisplayableVisitor uiDisplayableVisitor = new UiDisplayableVisitor();

        Pokemon chosenPokemon = pokemonResult.getResult();
        chosenPokemon.accept(uiDisplayableVisitor);
        player.setCurrentPokemon(chosenPokemon);

        return new OperationResult<String>().Ok(player.getName() + " changed pokemon to: " + uiDisplayableVisitor.getItemText());
    }

    @Override
    public @NotNull Menu<Pokemon> generateSubmenu() {
        UiDisplayableVisitor uiDisplayableVisitor = new UiDisplayableVisitor();
        Player player = gameState.getCurrentPlayer();

        List<MenuItem<Pokemon, ?>> items = U.map(player.getPokemons(), pokemon -> {
            pokemon.accept(uiDisplayableVisitor);
            return new MenuItem<>(uiDisplayableVisitor.getItemText(), pokemon);
        });
        return new Menu<>(items);
    }
}
