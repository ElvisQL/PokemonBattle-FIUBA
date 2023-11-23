package org.fiuba.algoritmos3.model.move;

import org.fiuba.algoritmos3.UiDisplayableVisitor;
import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.move.errors.OwnershipError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class UseItem extends GameMove {
    public final static String label = "Use Item";

    private final Item chosenItem;
    private final Pokemon chosenPokemon;

    public UseItem(Item chosenItem, Pokemon chosenPokemon) {
        this.chosenItem = chosenItem;
        this.chosenPokemon = chosenPokemon;
    }

    @Override
    public GameMoveResult<String> run(GameState gameState) {
        Player player = gameState.getCurrentPlayer();
        UiDisplayableVisitor uiDisplayableVisitor = new UiDisplayableVisitor();

        if (!player.getItems().contains(chosenItem)) {
            return new GameMoveResult<String>().Err(new OwnershipError("The selected item doesn't belong to the current player"));
        }

        try {
            chosenItem.use(chosenPokemon);
        } catch (BaseError e) {
            return new GameMoveResult<String>().Err(e);
        }

        chosenItem.accept(uiDisplayableVisitor);
        player.getItems().remove(chosenItem);

        return new GameMoveResult<String>().Ok(player.getName() + " used " + uiDisplayableVisitor.getItemText()
                + " against " + chosenPokemon.getName());
    }
}