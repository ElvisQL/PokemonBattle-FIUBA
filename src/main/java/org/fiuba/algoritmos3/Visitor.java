package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.model.pokemon.status.Status;

public interface Visitor {
    void visit(Pokemon pokemon);

    void visit(Item item);

    void visit(Status status);

    void visit(ConcreteSkill skill);
}
