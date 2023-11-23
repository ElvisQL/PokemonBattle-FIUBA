package org.fiuba.algoritmos3.controller.gameMove;

import javafx.stage.Stage;
import org.fiuba.algoritmos3.controller.PickerWrapperController;
import org.fiuba.algoritmos3.model.move.GameMove;
import org.fiuba.algoritmos3.model.move.builder.GameMoveBuilder;

public abstract class GameMoveController<T extends GameMove, B extends GameMoveBuilder<T>> extends PickerWrapperController {

    protected B builder;

    protected void executeGameMove() {
        T gameMove = builder.build();
        gameAPI.play(gameMove);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        changeScene(stage, getResource("views/chooseGameMove/choose-game-move-view.fxml"));
    }
}
