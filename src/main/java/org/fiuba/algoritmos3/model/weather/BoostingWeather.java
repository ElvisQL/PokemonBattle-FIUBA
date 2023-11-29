package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;

import java.util.List;

public abstract class BoostingWeather extends SkillModifier implements Weather {

    @Override
    public String use(Pokemon pokemon, Pokemon otherPokemon) throws BaseError {
        String msg = "";
        if (getSkillType() != AttackSkill.class && !boostedPokemonTypes().contains(pokemon.getType())) {
            msg += this.wrappee.use(pokemon, otherPokemon);
        }

        int oldHealth = otherPokemon.getHealth();
        msg += "\n" + this.wrappee.use(pokemon, otherPokemon);
        int currentHealth = otherPokemon.getHealth();

        int damageTaken = oldHealth - currentHealth;
        otherPokemon.setHealth((int) (currentHealth - damageTaken * 0.10));
        return msg;
    }

    protected abstract List<PokemonType> boostedPokemonTypes();

    public abstract String getName();
}
