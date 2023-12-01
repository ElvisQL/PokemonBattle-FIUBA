package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;

import java.util.List;

public class DamageBoosterModifier extends SkillModifier {

    private final List<PokemonType> boostedPokemonTypes;

    public DamageBoosterModifier(List<PokemonType> boostedPokemonTypes) {
        this.boostedPokemonTypes = boostedPokemonTypes;
    }

    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError {
        if (getSkillType() != AttackSkill.class && !boostedPokemonTypes.contains(pokemon.getType())) {
            this.wrappee.use(pokemon, otherPokemon, state);
        }

        int oldHealth = otherPokemon.getHealth();
        this.wrappee.use(pokemon, otherPokemon, state);
        int currentHealth = otherPokemon.getHealth();

        int damageTaken = oldHealth - currentHealth;
        otherPokemon.setHealth((int) (currentHealth - damageTaken * 0.10));
    }
}