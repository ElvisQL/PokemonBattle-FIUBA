package org.fiuba.algoritmos3.models.item;

import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

import static org.fiuba.algoritmos3.Constants.PERCENT;

public class IncreaseDefenseItem extends Item {

    private final Integer percentageIncrease;

    public IncreaseDefenseItem(Integer id, String name, String description, Integer percentageIncrease) {
        super(id, name, description);
        this.percentageIncrease = percentageIncrease;
    }

    @Override
    public void use(Pokemon pokemon) throws InvalidSelectionException {
        if (pokemon.isDead()) {
            throw new InvalidSelectionException("Pokemon");
        }
        Integer currentDefense = pokemon.getDefencePoints();
        Integer increaseDefense = currentDefense * percentageIncrease / PERCENT;
        pokemon.setDefencePoints(increaseDefense + currentDefense);
    }
}
