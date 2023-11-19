package org.fiuba.algoritmos3.game.model.weather;

import org.fiuba.algoritmos3.game.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.game.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.skills.SkillModifier;

import java.io.IOException;
import java.util.List;

public abstract class BoostingWeather extends SkillModifier implements Weather {

    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon) throws NoRemainingUsesError, IOException {
        if (getSkillType() != AttackSkill.class && !boostedPokemonTypes().contains(pokemon.getType())) {
            this.wrappee.use(pokemon, otherPokemon);
        }

        int oldHealth = otherPokemon.getHealth();
        this.wrappee.use(pokemon, otherPokemon);
        int currentHealth = otherPokemon.getHealth();

        int damageTaken = oldHealth - currentHealth;
        otherPokemon.setHealth((int) (currentHealth - damageTaken * 0.10));
    }

    protected abstract List<PokemonType> boostedPokemonTypes();

    public abstract String getName();
}
