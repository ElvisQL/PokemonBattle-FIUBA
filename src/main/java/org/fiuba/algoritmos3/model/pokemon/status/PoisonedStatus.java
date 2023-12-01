package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class PoisonedStatus implements ApplicableStatus {

    @Override
    public String getName() {
        return "Poisoned";
    }

    @Override
    public void apply(Pokemon pokemon) {
        int currentHealth = pokemon.getHealth();
        int maxHealth = pokemon.getMaxHealth();

        int damage = (int) (maxHealth * 0.05);

        int newHealth = currentHealth - damage;
        pokemon.setHealth(newHealth);
    }

}
