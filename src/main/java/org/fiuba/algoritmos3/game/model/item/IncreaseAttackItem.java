package org.fiuba.algoritmos3.game.model.item;

import org.fiuba.algoritmos3.game.error.InvalidSelectionException;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;

import static org.fiuba.algoritmos3.Constants.PERCENT;

public class IncreaseAttackItem extends Item {
    private final Integer percentageIncrease;

    public IncreaseAttackItem(Integer id, String name, String description, Integer percentageIncrease) {
        super(id, name, description);
        this.percentageIncrease = percentageIncrease;
    }

    @Override
    public void use(Pokemon pokemon) throws InvalidSelectionException {
        if (pokemon.isDead()) {
            throw new InvalidSelectionException("Pokemon");
        }

        Integer currentAttack = pokemon.getAttackPoints();
        Integer increaseAttack = currentAttack * percentageIncrease / PERCENT;
        pokemon.setAttackPoints(increaseAttack + currentAttack);
    }

}