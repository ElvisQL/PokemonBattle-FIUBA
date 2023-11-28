package org.fiuba.algoritmos3.controller.gameMove;

import javafx.beans.value.ObservableValue;
import org.fiuba.algoritmos3.controller.picker.PokemonPickerController;
import org.fiuba.algoritmos3.model.move.Surrender;
import org.fiuba.algoritmos3.model.move.builder.SurrenderBuilder;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.net.URL;
import java.util.ResourceBundle;

public class SurrenderController extends GameMoveController<Surrender, SurrenderBuilder> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new SurrenderBuilder();
        loadPicker(PokemonPickerController.class, null, null);
        executeGameMove();
    }


    private void onPokemonPicked(ObservableValue<?> _obs, Pokemon oldPokemon, Pokemon newPokemon) {
        executeGameMove();
    }

}
