package org.fiuba.algoritmos3.models.item;

import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;


public class RestoreStatusItem extends Item {

    public RestoreStatusItem(Integer id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public void use(Pokemon pokemon) throws InvalidSelectionException {
        if (pokemon.isDead()) {
            throw new InvalidSelectionException("Pokemon");
        }
        pokemon.clearStatuses();
    }
}