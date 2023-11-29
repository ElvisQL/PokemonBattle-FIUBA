package org.fiuba.algoritmos3.model.item;

import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import static org.fiuba.algoritmos3.Constants.PERCENT;

public class IncreaseDefenseItem extends Item {

    private final Integer percentageIncrease;

    public IncreaseDefenseItem(Integer id, String name, String description, Integer percentageIncrease) {
        super(id, name, description);
        this.percentageIncrease = percentageIncrease;
    }

    public Integer getPercentageIncrease() {
        return percentageIncrease;
    }

    @Override
    public String use(Pokemon pokemon) throws InvalidSelectionException {
        if (pokemon.isDead()) {
            throw new InvalidSelectionException("The user chose an invalid Pokemon");
        }
        Integer currentDefense = pokemon.getDefencePoints();
        Integer increaseDefense = currentDefense * percentageIncrease / PERCENT;
        pokemon.setDefencePoints(increaseDefense + currentDefense);
        return (pokemon.getName() + " has increased defense for "+ increaseDefense);
    }
}
