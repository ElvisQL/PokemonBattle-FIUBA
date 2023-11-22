package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.io.IOException;

public class AttackSkill extends ConcreteSkill {
    private Integer remainingUses;
    private final DamageCalculator damageCalculator;


    public AttackSkill(String name, Integer power, Integer remainingUses) {
        super(name);
        this.remainingUses = remainingUses;
        this.damageCalculator = new DamageCalculator(power);
    }

    @Override
    public void apply(Pokemon pokemonAttacker, Pokemon pokemonTarget) throws IOException, NoRemainingUsesError {
        if (remainingUses <= 0) {
            throw new NoRemainingUsesError();
        }
        Double damage = damageCalculator.calculateDamage(pokemonAttacker, pokemonTarget);
        remainingUses--;
        pokemonTarget.setHealth(pokemonTarget.getHealth() - damage.intValue());
    }


}

