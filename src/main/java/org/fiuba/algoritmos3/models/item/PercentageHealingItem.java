package org.fiuba.algoritmos3.models.item;

import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

public class PercentageHealingItem extends HealingItem {

    public PercentageHealingItem(Integer id, String name, String description, Integer healing) {
        super(id, name, description, healing);
    }

    @Override
    public void use(Pokemon pokemon) throws InvalidSelectionException {
        if (pokemon.isDead()) {
            throw new InvalidSelectionException("Pokemon");
        }

        int currentHealth = pokemon.getHealth();
        int increaseHealth = Math.min(currentHealth + (pokemon.getMaxHealth() * 100 / healing), pokemon.getMaxHealth());
        pokemon.setHealth(increaseHealth);
    }
}
