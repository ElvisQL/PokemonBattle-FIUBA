package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;

public abstract class UseSkillStatus extends SkillModifier implements Status {
    @Override
    public String use(Pokemon pokemon, Pokemon otherPokemon) throws BaseError {
        if (canUseSkill(pokemon)) {
            return this.wrappee.use(pokemon, otherPokemon);
        }
        return (pokemon.getName() + "hasnt use skill because its " + pokemon.getStatuses().toString());
    }

    public abstract boolean canUseSkill(Pokemon pokemon);

}
