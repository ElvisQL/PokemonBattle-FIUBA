package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.models.item.Item;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;
import org.fiuba.algoritmos3.models.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.models.pokemon.status.Status;

public class UiDisplayableVisitor implements Visitor, UIDisplayable {

    private String itemId;
    private String itemText;

    @Override
    public void visit(Pokemon pokemon) {
        itemId = pokemon.getName();
        itemText = pokemon.getName() + " (" + pokemon.getStatusDescription() + ")";
    }

    @Override
    public void visit(Item item) {
        itemId = item.getName();
        itemText = item.getName() + " (" + item.getDescription() + ")";

    }

    @Override
    public void visit(Status status) {
        itemId = status.getName();
        itemText = status.getName();
    }

    @Override
    public void visit(ConcreteSkill skill) {
        itemId = skill.getName();
        itemText = skill.getName();
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public String getItemText() {
        return itemText;
    }
}