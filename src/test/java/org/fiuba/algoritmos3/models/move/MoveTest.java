package org.fiuba.algoritmos3.models.move;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.move.ChangePokemon;
import org.fiuba.algoritmos3.model.move.GameMoveResult;
import org.fiuba.algoritmos3.model.move.Surrender;
import org.fiuba.algoritmos3.model.move.UseItem;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoveTest {

    @Mock
    private GameState gameState;

    @Mock
    private Player player;

    @Mock
    private Item chosenItem;

    @Mock
    private Pokemon chosenPokemon;

    @InjectMocks
    private UseItem useItem;

    @Test
    void testRun() throws InvalidSelectionException {
        GameState gameState = mock(GameState.class);
        Player currentPlayer = mock(Player.class);
        Pokemon chosenPokemon = mock(Pokemon.class);

        when(gameState.getCurrentPlayer()).thenReturn(currentPlayer);

        doNothing().when(currentPlayer).setCurrentPokemon(chosenPokemon);

        ChangePokemon changePokemonMove = new ChangePokemon(chosenPokemon);

        GameMoveResult<String> result = changePokemonMove.run(gameState);

        assert result.isOk() : "Expected a successful result";
        verify(currentPlayer).setCurrentPokemon(chosenPokemon);
    }

    @Test
    void testRunSurrender() {
        GameState gameState = mock(GameState.class);
        Player currentPlayer = mock(Player.class);
        when(gameState.getCurrentPlayer()).thenReturn(currentPlayer);
        Surrender surrenderMove = new Surrender();

        GameMoveResult<String> result = surrenderMove.run(gameState);

        assert result.isOk() : "Expected a successful result";
        verify(currentPlayer).surrender();
    }


    @Test
    public void testRunItemNotOwnedOwnershipError() {
        when(gameState.getCurrentPlayer()).thenReturn(player);
        when(player.getItems()).thenReturn(Collections.emptyList());

        GameMoveResult<String> result = useItem.run(gameState);

        assertEquals("The selected item doesn't belong to the current player", result.getError().getMessage());
    }

    @Test
    public void testRunSuccessfulUseOk() throws BaseError {
        when(gameState.getCurrentPlayer()).thenReturn(player);

        List<Item> items = new ArrayList<>();
        items.add(chosenItem);
        when(player.getItems()).thenReturn(items);

        GameMoveResult<String> result = useItem.run(gameState);

        assert result.isOk() : "Expected a successful result";

        verify(chosenItem).use(chosenPokemon);

        assertEquals("Used item", result.getResult());
    }

    @Test
    public void testRunUseItemThrowsErrorBaseError() throws BaseError {
        when(gameState.getCurrentPlayer()).thenReturn(player);
        when(player.getItems()).thenReturn(Collections.singletonList(chosenItem));
        doThrow(new BaseError("Item usage error")).when(chosenItem).use(chosenPokemon);

        GameMoveResult<String> result = useItem.run(gameState);

        assertEquals("Item usage error", result.getError().getMessage());
    }
}
