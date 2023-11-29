package org.fiuba.algoritmos3.model.item;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import static org.fiuba.algoritmos3.Constants.PERCENT;

public class IncreaseAttackItem extends Item {
    private final Integer percentageIncrease;

    public IncreaseAttackItem(Integer id, String name, String description, Integer percentageIncrease) {
        super(id, name, description);
        this.percentageIncrease = percentageIncrease;
    }

    public Integer getPercentageIncrease() {
        return percentageIncrease;
    }

    @Override
    public void use(Pokemon pokemon, GameState gameState) throws InvalidSelectionException {
        if (pokemon.isDead()) {
            throw new InvalidSelectionException("Pokemon");
        }

        Integer currentAttack = pokemon.getAttackPoints();
        Integer increaseAttack = currentAttack * percentageIncrease / PERCENT;
        pokemon.setAttackPoints(increaseAttack + currentAttack);
        gameState.setAdittionalMsg(pokemon.getName() + " has increased its attack for " + increaseAttack);

    }

}