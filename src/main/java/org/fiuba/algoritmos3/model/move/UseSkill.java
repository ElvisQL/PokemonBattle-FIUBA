package org.fiuba.algoritmos3.model.move;

import com.github.underscore.U;
import org.fiuba.algoritmos3.UiDisplayableVisitor;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.menu.Menu;
import org.fiuba.algoritmos3.model.menu.MenuItem;
import org.fiuba.algoritmos3.model.menu.operation.OperationResult;
import org.fiuba.algoritmos3.model.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.NullSkillModifier;
import org.fiuba.algoritmos3.model.pokemon.skills.SkillModifier;
import org.fiuba.algoritmos3.model.pokemon.status.Status;
import org.fiuba.algoritmos3.model.weather.Weather;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class UseSkill extends GameMove<String, ConcreteSkill> {
    public final static String label = "Use Skill";

    public UseSkill(GameState gameState) {
        super(gameState);
    }

    @Override
    public OperationResult<String> run(UserInterface ui, OperationResult<ConcreteSkill> skillResult) throws NoRemainingUsesError, IOException {
        if (skillResult.isErr()) {
            return new OperationResult<String>().Err(skillResult.getError());
        }
        Pokemon pokemon = gameState.getCurrentPlayer().getCurrentPokemon();
        Pokemon opponentPokemon = gameState.getCurrentPlayer().getOpponent().getCurrentPokemon();
        ConcreteSkill chosenSkill = skillResult.getResult();

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

        skill.use(pokemon, opponentPokemon);

        return new OperationResult<String>().Ok(pokemon.getName() + " used " + chosenSkill.getName());
    }


    @Override
    public @NotNull Menu<ConcreteSkill> generateSubmenu() {
        Pokemon pokemon = gameState.getCurrentPlayer().getCurrentPokemon();
        UiDisplayableVisitor uiDisplayableVisitor = new UiDisplayableVisitor();

        List<MenuItem<ConcreteSkill, ?>> items = U.map(pokemon.getSkills(), skill -> {
            skill.accept(uiDisplayableVisitor);
            return new MenuItem<>(uiDisplayableVisitor.getItemText(), skill);
        });
        return new Menu<>(items);
    }
}