package org.fiuba.algoritmos3.controller.gameMove;

import javafx.stage.Stage;
import org.fiuba.algoritmos3.controller.PickerWrapperController;
import org.fiuba.algoritmos3.controller.WinnerViewController;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.move.GameMove;
import org.fiuba.algoritmos3.model.move.builder.GameMoveBuilder;

public abstract class GameMoveController<T extends GameMove, B extends GameMoveBuilder<T>> extends PickerWrapperController {

    protected B builder;

    protected void executeGameMove() {
        T gameMove = builder.build();

        gameAPI.play(gameMove);

        Stage stage = (Stage) rootPane.getScene().getWindow();

        Player winner = gameAPI.getWinner();
        if (winner != null) {
            WinnerViewController controller = (WinnerViewController) changeScene(stage, getResource("views/winner-view.fxml"));
            controller.setWinner(winner);
            return;
        }

        changeScene(stage, getResource("views/chooseGameMove/choose-game-move-view.fxml"));
    }
}
