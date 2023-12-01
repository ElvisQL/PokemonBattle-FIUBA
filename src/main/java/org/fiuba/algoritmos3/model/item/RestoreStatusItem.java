package org.fiuba.algoritmos3.model.item;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;


public class RestoreStatusItem extends Item {

    public RestoreStatusItem(Integer id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public void use(Pokemon pokemon, GameState gameState) throws InvalidSelectionException {
        if (!canUse(pokemon))
            throw new InvalidSelectionException("Pokemon");

        pokemon.clearStatuses();
        gameState.setAdditionalMsg(pokemon.getName() + " has restored all its states");

    }

    @Override
    public boolean canUse(Pokemon pokemon) {
        return !pokemon.isDead() && !pokemon.getStatuses().isEmpty();
    }
}