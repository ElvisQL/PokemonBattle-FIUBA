package org.fiuba.algoritmos3.controller.gameMove;

import javafx.beans.value.ObservableValue;
import org.fiuba.algoritmos3.controller.picker.PokemonPickerController;
import org.fiuba.algoritmos3.model.move.ChangePokemon;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePokemonController extends GameMoveController<ChangePokemon> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPicker(PokemonPickerController.class, gameAPI.currentPlayer().getPokemons(), this::onPokemonPicked);
    }


    @Override
    void executeMove() {

    }

    private void onPokemonPicked(ObservableValue<?> _obs, Pokemon oldPokemon, Pokemon newPokemon) {

    }

}
