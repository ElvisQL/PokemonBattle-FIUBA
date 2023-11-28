package org.fiuba.algoritmos3.controller.gameMove;

import org.fiuba.algoritmos3.controller.picker.BooleanPickerController;
import org.fiuba.algoritmos3.model.move.Surrender;
import org.fiuba.algoritmos3.model.move.builder.SurrenderBuilder;

import java.net.URL;
import java.util.ResourceBundle;

public class SurrenderController extends GameMoveController<Surrender, SurrenderBuilder> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new SurrenderBuilder();
        BooleanPickerController controller = (BooleanPickerController) loadPicker(BooleanPickerController.class, null);
        controller.setQuestion("Are you sure you want to surrender?");
        controller.addSelectionListener((_a, _b, _c) -> executeGameMove());
        controller.addBackListener((_a, _b, _c) -> loadChooseGameMove());
    }

}
