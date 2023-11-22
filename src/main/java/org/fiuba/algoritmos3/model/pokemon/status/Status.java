package org.fiuba.algoritmos3.model.pokemon.status;

import org.fiuba.algoritmos3.Visitor;

public interface Status {

    String getName();

    void accept(Visitor visitor);
}

