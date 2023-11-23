package org.fiuba.algoritmos3.model.item;

import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public abstract class HealingItem extends Item {
    protected final Integer healing;

    public HealingItem(Integer id, String name, String description, Integer healing) {
        super(id, name, description);
        this.healing = healing;
    }

    @Override
    public abstract void use(Pokemon pokemon) throws InvalidSelectionException;

}
