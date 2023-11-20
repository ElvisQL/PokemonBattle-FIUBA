package org.fiuba.algoritmos3.game.move.choose_item;

import com.github.underscore.U;
import org.fiuba.algoritmos3.UiDisplayableVisitor;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.game.GameState;
import org.fiuba.algoritmos3.game.menu.Menu;
import org.fiuba.algoritmos3.game.menu.MenuItem;
import org.fiuba.algoritmos3.game.menu.operation.OperationResult;
import org.fiuba.algoritmos3.game.menu.operation.errors.BaseError;
import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.item.Item;
import org.fiuba.algoritmos3.game.model.item.RestoreStatusItem;
import org.fiuba.algoritmos3.game.model.item.ReviveItem;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.move.GameMove;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChooseItem extends GameMove<Pair<Item, Pokemon>, Pokemon> {
    private final Item item;
    private final Player player;

    UiDisplayableVisitor visitor = new UiDisplayableVisitor();

    public ChooseItem(GameState gameState, Item item) {
        super(gameState);

        this.item = item;
        this.player = gameState.getCurrentPlayer();
    }


    @Override
    public OperationResult<Pair<Item, Pokemon>> run(UserInterface ui, OperationResult<Pokemon> submenuResult) {
        if (submenuResult.isErr()) {
            return new OperationResult<Pair<Item, Pokemon>>().Err(submenuResult.getError());
        }

        Pokemon chosenPokemon = submenuResult.getResult();

        return new OperationResult<Pair<Item, Pokemon>>().Ok(new Pair<>(item, chosenPokemon));
    }

    @Override
    public @NotNull Menu<Pokemon> generateSubmenu() throws BaseError {
        List<Pokemon> pokemons = player.getPokemons();

        if (item.getClass().equals(ReviveItem.class)) {
            pokemons = U.filter(pokemons, Pokemon::isDead);
        } else if (item.getClass().equals(RestoreStatusItem.class)) {
            pokemons = U.filter(pokemons, pokemon -> !pokemon.getStatuses().isEmpty() && !pokemon.isDead());
        }

        if (pokemons.isEmpty()) throw new BaseError("This item cannot be applied to any Pokemon.");

        List<MenuItem<Pokemon, ?>> items = U.map(pokemons, pokemon -> {
            pokemon.accept(visitor);

            return new MenuItem<>(visitor.getItemText(), pokemon);
        });
        return new Menu<>(items);
    }
}