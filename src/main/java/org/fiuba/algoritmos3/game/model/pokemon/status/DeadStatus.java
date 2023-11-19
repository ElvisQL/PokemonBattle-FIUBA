package org.fiuba.algoritmos3.game.model.pokemon.status;

import org.fiuba.algoritmos3.Visitor;

public class DeadStatus implements Status {
    @Override
    public String getName() {
        return "Dead";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
