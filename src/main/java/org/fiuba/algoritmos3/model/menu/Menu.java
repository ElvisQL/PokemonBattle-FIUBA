package org.fiuba.algoritmos3.model.menu;

import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.menu.operation.OperationResult;

import java.io.IOException;
import java.util.List;

public class Menu<T> {
    public final List<MenuItem<T, ?>> items;

    public Menu(List<MenuItem<T, ?>> items) {
        this.items = items;
    }

    public OperationResult<T> show(UserInterface ui) throws IOException, InvalidSelectionException {
        if (isEmpty()) return new OperationResult<T>().Ok(null);
        MenuItem<T, ?> chosenItem = ui.chooseOption("choose an option: ", items);
        return chosenItem.runOperation(ui);
    }

    public Boolean isEmpty() {
        return items == null || items.isEmpty();
    }
}