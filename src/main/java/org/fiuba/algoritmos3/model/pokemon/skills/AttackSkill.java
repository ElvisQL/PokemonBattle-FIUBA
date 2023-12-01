package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.move.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class AttackSkill extends ConcreteSkill {
    private Integer remainingUses;
    private final DamageCalculator damageCalculator;


    public AttackSkill(String name, Integer power, Integer remainingUses, String description) {
        super(name, description, remainingUses);
        this.remainingUses = remainingUses;
        this.damageCalculator = new DamageCalculator(power);

    }

    public Integer getRemainingUses() {
        return remainingUses;
    }

    @Override
    public void apply(Pokemon pokemonAttacker, Pokemon pokemonTarget, GameState state) throws BaseError {
        if (remainingUses <= 0) {
            throw new NoRemainingUsesError();
        }
        Double damage = damageCalculator.calculateDamage(pokemonAttacker, pokemonTarget);
        remainingUses--;
        pokemonTarget.setHealth(pokemonTarget.getHealth() - damage.intValue());
        state.setAdittionalMsg(pokemonAttacker.getName() + " damaged " + pokemonTarget.getName() + " by -" + damage.intValue());
    }
}

