package org.fiuba.algoritmos3.game.menu.operation;

import org.fiuba.algoritmos3.game.menu.operation.errors.BaseError;

public class OperationResult<T> {

    private T result;
    private BaseError error;

    public OperationResult() {
    }

    private OperationResult(T result, BaseError error) {
        this.result = result;
        this.error = error;
    }

    public OperationResult<T> Ok(T result) {
        return new OperationResult<>(result, null);
    }

    public OperationResult<T> Err(BaseError error) {
        return new OperationResult<>(null, error);
    }

    public T getResult() {
        assert isOk();
        return result;
    }

    public BaseError getError() {
        assert isErr();
        return error;
    }


    public Boolean isOk() {
        return error == null;
    }

    public Boolean isErr() {
        return !isOk();
    }
}
