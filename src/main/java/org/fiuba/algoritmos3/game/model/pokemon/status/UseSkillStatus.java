package org.fiuba.algoritmos3.game.model.pokemon.status;

import org.fiuba.algoritmos3.game.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.skills.SkillModifier;

import java.io.IOException;

public abstract class UseSkillStatus extends SkillModifier implements Status {
    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon) throws NoRemainingUsesError, IOException {
        if (canUseSkill(pokemon)) {
            this.wrappee.use(pokemon, otherPokemon);
        }
    }

    public abstract boolean canUseSkill(Pokemon pokemon);

}
