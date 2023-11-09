package org.fiuba.algoritmos3.models.pokemon.status;

import org.fiuba.algoritmos3.Visitor;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

public class PoisonedStatus implements ApplyableStatus {

    @Override
    public void apply(Pokemon pokemon) {
        int currentHealth = pokemon.getHealth();
        int maxHealth = pokemon.getMaxHealth();

        int damage = (int) (maxHealth * 0.05);

        int newHealth = currentHealth - damage;
        pokemon.setHealth(newHealth);

    }

    @Override
    public String getName() {
        return "Poisoned";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
