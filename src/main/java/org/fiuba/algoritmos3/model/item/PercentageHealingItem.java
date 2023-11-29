package org.fiuba.algoritmos3.model.item;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class PercentageHealingItem extends HealingItem {

    public PercentageHealingItem(Integer id, String name, String description, Integer healing) {
        super(id, name, description, healing);
    }

    @Override
    public void use(Pokemon pokemon, GameState gameState) throws InvalidSelectionException {
        if (!canUse(pokemon))
            throw new InvalidSelectionException("Pokemon");

        int currentHealth = pokemon.getHealth();
        int increaseHealth = Math.min(currentHealth + (pokemon.getMaxHealth() * 100 / healing), pokemon.getMaxHealth());
        pokemon.setHealth(increaseHealth);
        gameState.setAdittionalMsg(pokemon.getName() + " has increased its health for " + increaseHealth);

    }

    @Override
    public boolean canUse(Pokemon pokemon) {
        return !pokemon.isDead();
    }
}
