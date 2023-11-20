package org.fiuba.algoritmos3.game.model.pokemon.status;

import org.fiuba.algoritmos3.Visitor;

public interface Status {

    String getName();

    void accept(Visitor visitor);
}

