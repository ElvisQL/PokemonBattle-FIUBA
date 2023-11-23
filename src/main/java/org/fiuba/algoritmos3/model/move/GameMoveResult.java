package org.fiuba.algoritmos3.model.move;

import org.fiuba.algoritmos3.model.error.BaseError;

public class GameMoveResult<T> {

    private T result;
    private BaseError error;

    public GameMoveResult() {
    }

    private GameMoveResult(T result, BaseError error) {
        this.result = result;
        this.error = error;
    }

    public GameMoveResult<T> Ok(T result) {
        return new GameMoveResult<>(result, null);
    }

    public GameMoveResult<T> Err(BaseError error) {
        return new GameMoveResult<>(null, error);
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
