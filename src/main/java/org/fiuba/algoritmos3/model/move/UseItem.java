package org.fiuba.algoritmos3.model.move;

import com.github.underscore.U;
import org.fiuba.algoritmos3.UiDisplayableVisitor;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.menu.Menu;
import org.fiuba.algoritmos3.model.menu.MenuItem;
import org.fiuba.algoritmos3.model.menu.operation.OperationResult;
import org.fiuba.algoritmos3.model.menu.operation.errors.OwnershipError;
import org.fiuba.algoritmos3.model.move.choose_item.ChooseItem;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UseItem extends GameMove<String, Pair<Item, Pokemon>> {
    public final static String label = "Use Item";

    public UseItem(GameState gameState) {
        super(gameState);
    }


    @Override
    public OperationResult<String> run(UserInterface ui, OperationResult<Pair<Item, Pokemon>> submenuResult) throws InvalidSelectionException {
        if (submenuResult.isErr()) {
            return new OperationResult<String>().Err(submenuResult.getError());
        }
        Player player = gameState.getCurrentPlayer();
        UiDisplayableVisitor uiDisplayableVisitor = new UiDisplayableVisitor();

        Item chosenItem = submenuResult.getResult().getValue0();
        Pokemon chosenPokemon = submenuResult.getResult().getValue1();

        if (!player.getItems().contains(chosenItem)) {
            return new OperationResult<String>().Err(new OwnershipError("The selected item doesn't belong to the current player"));
        }

        chosenItem.use(chosenPokemon);

        chosenItem.accept(uiDisplayableVisitor);
        player.getItems().remove(chosenItem);

        return new OperationResult<String>().Ok(player.getName() + " used " + uiDisplayableVisitor.getItemText()
                + " against " + chosenPokemon.getName());
    }

    @Override
    public @NotNull Menu<Pair<Item, Pokemon>> generateSubmenu() {
        Player player = gameState.getCurrentPlayer();
        UiDisplayableVisitor uiDisplayableVisitor = new UiDisplayableVisitor();

        List<MenuItem<Pair<Item, Pokemon>, ?>> items = U.map(player.getItems(), item -> {
            item.accept(uiDisplayableVisitor);

            return new MenuItem<>(uiDisplayableVisitor.getItemText(), new ChooseItem(gameState, item));
        });
        return new Menu<>(items);
    }

}