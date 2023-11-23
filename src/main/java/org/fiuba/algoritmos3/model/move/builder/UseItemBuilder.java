package org.fiuba.algoritmos3.model.move.builder;

import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.move.UseItem;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public class UseItemBuilder implements GameMoveBuilder<UseItem> {

    private Item item;
    private Pokemon targetPokemon;

    public void setItem(Item item) {
        this.item = item;
    }

    public void setTargetPokemon(Pokemon targetPokemon) {
        this.targetPokemon = targetPokemon;
    }

    @Override
    public UseItem build() {
        return new UseItem(item, targetPokemon);
    }

}
