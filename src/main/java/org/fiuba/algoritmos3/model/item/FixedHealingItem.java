package org.fiuba.algoritmos3.model.item;

import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class FixedHealingItem extends HealingItem {
    public FixedHealingItem(Integer id, String name, String description, Integer healing) {
        super(id, name, description, healing);
    }

    @Override
    public String use(Pokemon pokemon) throws InvalidSelectionException {
        if (pokemon.isDead()) {
            throw new InvalidSelectionException("Pokemon");
        }

        int currentHealth = pokemon.getHealth();
        int increaseHealth = Math.min(currentHealth + healing, pokemon.getMaxHealth());
        pokemon.setHealth(increaseHealth);

        return (pokemon.getName() + "has fixed its health: " + "+"+ healing);


    }
}
