package org.fiuba.algoritmos3.models.pokemon.status;

import org.fiuba.algoritmos3.Visitor;

public interface Status {

    String getName();

    void accept(Visitor visitor);
}

