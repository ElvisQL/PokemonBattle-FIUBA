package org.fiuba.algoritmos3.model.weather;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;

import java.util.List;

public abstract class BoostingWeather extends SkillModifier implements Weather {

    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError {
        String msg = "";
        if (getSkillType() != AttackSkill.class && !boostedPokemonTypes().contains(pokemon.getType())) {
            this.wrappee.use(pokemon, otherPokemon,state);
        }

        int oldHealth = otherPokemon.getHealth();
        msg += state.getAdditionalMsg();
        this.wrappee.use(pokemon, otherPokemon,state);
        int currentHealth = otherPokemon.getHealth();

        int damageTaken = oldHealth - currentHealth;
        otherPokemon.setHealth((int) (currentHealth - damageTaken * 0.10));
        msg+=state.getAdditionalMsg();
        state.setAdittionalMsg(msg);

    }

    protected abstract List<PokemonType> boostedPokemonTypes();

    public abstract String getName();
}
