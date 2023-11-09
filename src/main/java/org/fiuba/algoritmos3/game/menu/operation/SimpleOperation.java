package org.fiuba.algoritmos3.game.menu.operation;

import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.game.menu.Menu;
import org.jetbrains.annotations.NotNull;


public class SimpleOperation<T, P> implements Operation<T, P> {
    private final T content;

    public SimpleOperation(T content) {
        this.content = content;
    }

    @Override
    public OperationResult<T> run(UserInterface ui, OperationResult<P> _null) {
        return new OperationResult<T>().Ok(content);
    }

    @Override
    public @NotNull Menu<P> generateSubmenu() {
        return new Menu<>(null);
    }
}
