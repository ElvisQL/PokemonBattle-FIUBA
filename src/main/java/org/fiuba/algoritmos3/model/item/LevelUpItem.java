package org.fiuba.algoritmos3.model.item;

import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class LevelUpItem extends Item {

    private final Integer increment;

    public LevelUpItem(Integer id, String name, String description, Integer inc) {
        super(id, name, description);
        this.increment = inc;
    }

    @Override
    public void use(Pokemon pokemon) throws InvalidSelectionException {
        if (pokemon.isDead()) {
            throw new InvalidSelectionException("Pokemon");
        }
        pokemon.levelUp(this.increment);
    }
}