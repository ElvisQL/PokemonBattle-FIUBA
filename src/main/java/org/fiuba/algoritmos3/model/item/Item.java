package org.fiuba.algoritmos3.model.item;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

public abstract class Item {

    protected final String name;
    protected final String description;


    protected final Integer id;

    protected Item(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public abstract void use(Pokemon pokemon , GameState gameState) throws BaseError;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}