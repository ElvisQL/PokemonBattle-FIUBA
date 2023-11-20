package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.game.model.item.Item;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.game.model.pokemon.status.Status;

public interface Visitor {
    void visit(Pokemon pokemon);

    void visit(Item item);

    void visit(Status status);

    void visit(ConcreteSkill skill);
}
