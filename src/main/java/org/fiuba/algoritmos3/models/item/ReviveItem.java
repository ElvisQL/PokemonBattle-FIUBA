package org.fiuba.algoritmos3.models.item;

import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

import static org.fiuba.algoritmos3.Constants.PERCENT;

public class ReviveItem extends Item {
    private final Integer restoredHealth;

    public ReviveItem(Integer id, String name, String description, Integer percentage) {
        super(id, name, description);
        this.restoredHealth = percentage;
    }

    @Override
    public void use(Pokemon pokemon) throws InvalidSelectionException {
        if (!pokemon.isDead()) {
            throw new InvalidSelectionException("The pokemon has to be weakened");
        }
        pokemon.clearStatuses();
        pokemon.setHealth(pokemon.getMaxHealth() * this.restoredHealth / PERCENT);
    }
}