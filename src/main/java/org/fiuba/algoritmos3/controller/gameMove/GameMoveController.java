package org.fiuba.algoritmos3.controller.gameMove;

import org.fiuba.algoritmos3.controller.PickerWrapperController;
import org.fiuba.algoritmos3.model.move.GameMove;

public abstract class GameMoveController<T extends GameMove> extends PickerWrapperController {

    private T gameMove;


    abstract void executeMove();

}
