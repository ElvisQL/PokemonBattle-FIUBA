package org.fiuba.algoritmos3.game.menu;

import org.fiuba.algoritmos3.UIDisplayable;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.game.menu.operation.Operation;
import org.fiuba.algoritmos3.game.menu.operation.OperationResult;
import org.fiuba.algoritmos3.game.menu.operation.SimpleOperation;
import org.fiuba.algoritmos3.game.menu.operation.errors.BaseError;

import java.io.IOException;

public class MenuItem<T, SubmenuType> implements UIDisplayable {
    private final String label;
    private final Operation<T, SubmenuType> operation;

    public MenuItem(String label, Operation<T, SubmenuType> operation) {
        this.label = label;
        this.operation = operation;
    }

    public MenuItem(String label, T content) {
        this.label = label;
        this.operation = new SimpleOperation<>(content);
    }

    public OperationResult<T> runOperation(UserInterface ui) throws IOException, InvalidSelectionException {
        try {
            return operation.run(ui, operation.generateSubmenu().show(ui));
        } catch (BaseError e) {
            return new OperationResult<T>().Err(e);
        }
    }

    @Override
    public String getItemId() {
        return label;
    }

    @Override
    public String getItemText() {
        return label;
    }
}