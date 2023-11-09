package org.fiuba.algoritmos3.game.menu.operation;

import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.errors.InvalidSelectionException;
import org.fiuba.algoritmos3.game.menu.Menu;
import org.fiuba.algoritmos3.game.menu.operation.errors.BaseError;
import org.fiuba.algoritmos3.game.menu.operation.errors.NoRemainingUsesError;

import java.io.IOException;

public interface Operation<T, S> {

    OperationResult<T> run(UserInterface ui, OperationResult<S> submenuResult) throws InvalidSelectionException, IOException, NoRemainingUsesError;

    Menu<S> generateSubmenu() throws BaseError;
}