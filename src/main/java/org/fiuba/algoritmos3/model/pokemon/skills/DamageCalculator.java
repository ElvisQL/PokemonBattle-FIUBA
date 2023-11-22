package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.io.IOException;
import java.util.HashMap;

import static org.fiuba.algoritmos3.Constants.*;

public class DamageCalculator {
    private final Integer power;

    public DamageCalculator(Integer power) {
        this.power = power;
    }

    public Double calculateDamage(Pokemon pokemonAttacker, Pokemon pokemonTarget) throws IOException {
        HashMap<PokemonType, HashMap<PokemonType, Double>> efficacyHash = EfficacyTableLoader.load();

        Double efficacyType = getEfficacyType(pokemonAttacker.getType(), pokemonTarget.getType(), efficacyHash);
        Double sameType = sameType(pokemonAttacker, pokemonTarget);
        Double randomAttack = generateRandomAttack();
        Double criticalAttack = generateCritical();

        return calculateDamage(pokemonAttacker.getLevel(), sameType, randomAttack, pokemonAttacker.getAttackPoints(),
                pokemonTarget.getDefencePoints(), criticalAttack, efficacyType);
    }

    private Double calculateDamage(Integer level, Double sameType, Double randomAttack,
                                   Integer attackPoints, Integer defencePoints, Double criticalAttack, Double efficacyType) {
        return (((((2 * level * criticalAttack * power *
                ((double) attackPoints / (double) defencePoints)) / 5) + 2) / 50) * sameType * efficacyType * randomAttack);
    }

    private Double generateRandomAttack() {
        return RANDOM_ATTACK_FACTOR;
    }

    private Double getEfficacyType(PokemonType type1, PokemonType type2, HashMap<PokemonType, HashMap<PokemonType,
            Double>> efficacyHash) {
        return efficacyHash.get(type1).get(type2);
    }

    private Double generateCritical() {
        if (Math.random() <= CRITICAL_CHANCE) {
            return CRITICAL_ATTACK;
        } else {
            return NOT_CRITICAL_ATTACK;
        }
    }

    private Double sameType(Pokemon pokemonAttacker, Pokemon other) {
        if (other.getType().equals(pokemonAttacker.getType())) {
            return SAME_TYPE;
        } else {
            return NOT_SAME_TYPE;
        }
    }
}
