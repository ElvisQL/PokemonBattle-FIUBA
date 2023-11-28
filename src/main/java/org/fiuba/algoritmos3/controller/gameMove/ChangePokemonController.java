package org.fiuba.algoritmos3.controller.gameMove;

import javafx.beans.value.ObservableValue;
import org.fiuba.algoritmos3.controller.picker.PokemonPickerController;
import org.fiuba.algoritmos3.model.move.ChangePokemon;
import org.fiuba.algoritmos3.model.move.builder.ChangePokemonBuilder;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePokemonController extends GameMoveController<ChangePokemon, ChangePokemonBuilder> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new ChangePokemonBuilder();
        loadPicker(PokemonPickerController.class, gameAPI.currentPlayer().getPokemons(), this::onPokemonPicked, (_a, _b, _c) -> loadChooseGameMove());
    }

    private void onPokemonPicked(ObservableValue<?> _obs, Pokemon oldPokemon, Pokemon newPokemon) {
        builder.setNewPokemon(newPokemon);
        executeGameMove();
    }

}
