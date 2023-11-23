package org.fiuba.algoritmos3.model.move;

import org.fiuba.algoritmos3.UiDisplayableVisitor;
import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class ChangePokemon extends GameMove {
    public final static String label = "Change Pokemon";

    private final Pokemon chosenPokemon;

    public ChangePokemon(Pokemon selectedPokemon) {
        this.chosenPokemon = selectedPokemon;
    }

    @Override
    public GameMoveResult<String> run(GameState gameState) {
        Player player = gameState.getCurrentPlayer();
        UiDisplayableVisitor uiDisplayableVisitor = new UiDisplayableVisitor();

        try {
            player.setCurrentPokemon(chosenPokemon);
        } catch (InvalidSelectionException e) {
            return new GameMoveResult<String>().Err(e);
        }

        return new GameMoveResult<String>().Ok(player.getName() + " changed pokemon to: " + uiDisplayableVisitor.getItemText());
    }

}
