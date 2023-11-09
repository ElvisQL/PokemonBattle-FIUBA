package org.fiuba.algoritmos3.models.item;

import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

public abstract class HealingItem extends Item {
    protected final Integer healing;

    public HealingItem(Integer id, String name, String description, Integer healing) {
        super(id, name, description);
        this.healing = healing;
    }

    @Override
    public abstract void use(Pokemon pokemon) throws InvalidSelectionException;

}
