package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;

public abstract class UseSkillStatus extends SkillModifier implements Status {
    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError {
        if (canUseSkill(pokemon)) {
            this.wrappee.use(pokemon, otherPokemon, state);
        }
        state.setAdittionalMsg(pokemon.getName() + " hasn't use skill because its affected with statuses");

    }

    public abstract boolean canUseSkill(Pokemon pokemon);

}
