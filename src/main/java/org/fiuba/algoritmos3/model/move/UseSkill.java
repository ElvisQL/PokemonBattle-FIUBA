package org.fiuba.algoritmos3.model.move;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.NullSkillModifier;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;
import org.fiuba.algoritmos3.model.pokemon.status.Status;
import org.fiuba.algoritmos3.model.weather.Weather;

public class UseSkill extends GameMove {
    public final static String label = "Use Skill";

    private final ConcreteSkill chosenSkill;

    public UseSkill(ConcreteSkill chosenSkill) {
        this.chosenSkill = chosenSkill;
    }

    @Override
    public GameMoveResult<String> run(GameState gameState) {
        Pokemon pokemon = gameState.getCurrentPlayer().getCurrentPokemon();
        Pokemon opponentPokemon = gameState.getCurrentPlayer().getOpponent().getCurrentPokemon();

        SkillModifier skill = new NullSkillModifier().wrap(chosenSkill);
        for (Status status : pokemon.getStatuses()) {
            if (status instanceof SkillModifier modifier) {
                skill = modifier.wrap(skill);
            }
        }

        Weather weather = gameState.getWeather();
        if (weather instanceof SkillModifier modifier) {
            skill = modifier.wrap(skill);
        }
        String msg;
        try {
             msg = skill.use(pokemon, opponentPokemon);
        } catch (BaseError e) {
            return new GameMoveResult<String>().Err(e);
        }

        return new GameMoveResult<String>().Ok(pokemon.getName() + " used " + chosenSkill.getName() + "\n" + msg);
    }
}