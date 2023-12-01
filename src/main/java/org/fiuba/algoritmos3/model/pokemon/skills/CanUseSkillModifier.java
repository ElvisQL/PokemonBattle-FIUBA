package org.fiuba.algoritmos3.model.pokemon.skills;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.util.function.Predicate;

public class CanUseSkillModifier extends SkillModifier {

    private final Predicate<Pokemon> canUseSkill;

    public CanUseSkillModifier(Predicate<Pokemon> canUseSkill) {
        this.canUseSkill = canUseSkill;
    }

    @Override
    public void use(Pokemon pokemon, Pokemon otherPokemon, GameState state) throws BaseError {
        if (canUseSkill.test(pokemon)) {
            this.wrappee.use(pokemon, otherPokemon, state);
        } else {
            state.setAdditionalMsg(pokemon.getName() + " couldn't use the skill because of it's statuses");
        }
    }

}