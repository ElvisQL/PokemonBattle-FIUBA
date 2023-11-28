package org.fiuba.algoritmos3.controller.gameMove;

import javafx.beans.value.ObservableValue;
import org.fiuba.algoritmos3.controller.picker.BooleanPickerController;
import org.fiuba.algoritmos3.model.move.Surrender;
import org.fiuba.algoritmos3.model.move.builder.SurrenderBuilder;

import java.net.URL;
import java.util.ResourceBundle;

public class SurrenderController extends GameMoveController<Surrender, SurrenderBuilder> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new SurrenderBuilder();
        BooleanPickerController controller = (BooleanPickerController) loadPicker(BooleanPickerController.class, null, this::onBooleanChosen);
        controller.setQuestion("Are you sure you want to surrender?");
    }


    private void onBooleanChosen(ObservableValue<?> _obs, Boolean oldPokemon, Boolean newPokemon) {
        if (newPokemon)
            executeGameMove();
        else
            return; // TODO call back listener
    }

}
