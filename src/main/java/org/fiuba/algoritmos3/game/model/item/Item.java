package org.fiuba.algoritmos3.game.model.item;

import org.fiuba.algoritmos3.Visitor;
import org.fiuba.algoritmos3.game.error.InvalidSelectionException;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;

public abstract class Item {

    protected final String name;
    protected final String description;


    protected final Integer id;

    protected Item(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public abstract void use(Pokemon pokemon) throws InvalidSelectionException;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}