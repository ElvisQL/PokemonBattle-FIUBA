package org.fiuba.algoritmos3.controller.gameMove;

import javafx.beans.value.ObservableValue;
import org.fiuba.algoritmos3.controller.picker.PickerController;
import org.fiuba.algoritmos3.controller.picker.PokemonPickerController;
import org.fiuba.algoritmos3.model.move.ChangePokemon;
import org.fiuba.algoritmos3.model.move.builder.ChangePokemonBuilder;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChangePokemonController extends GameMoveController<ChangePokemon, ChangePokemonBuilder> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new ChangePokemonBuilder();

        List<Pokemon> availablePokemons = gameAPI.currentPlayer().getPokemons().stream()
                .filter((pokemon -> !pokemon.isDead()))
                .toList();
        PickerController<Pokemon> controller = loadPicker(PokemonPickerController.class, availablePokemons);

        controller.addSelectionListener(this::onPokemonPicked);
        controller.addBackListener((_a, _b, _c) -> loadChooseGameMove());
        controller.setFilterFunction((pokemon -> !pokemon.isDead()));
    }

    private void onPokemonPicked(ObservableValue<?> _obs, Pokemon oldPokemon, Pokemon newPokemon) {
        builder.setNewPokemon(newPokemon);
        executeGameMove();
    }

}
