package org.fiuba.algoritmos3.controller.gameMove;

import javafx.beans.value.ObservableValue;
import org.fiuba.algoritmos3.controller.picker.ItemPickerController;
import org.fiuba.algoritmos3.controller.picker.PokemonPickerController;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.move.UseItem;
import org.fiuba.algoritmos3.model.move.builder.UseItemBuilder;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class UseItemController extends GameMoveController<UseItem, UseItemBuilder> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new UseItemBuilder();
        loadPicker(ItemPickerController.class, gameAPI.currentPlayer().getItems(), this::onItemPicked);
    }

    private void onItemPicked(ObservableValue<?> _obs, Item oldItem, Item newItem) {
        builder.setItem(newItem);
        List<Pokemon> pokemons = Stream.concat(
                gameAPI.currentPlayer().getPokemons().stream(),
                gameAPI.currentPlayer().getOpponent().getPokemons().stream()
        ).toList();
        loadPicker(PokemonPickerController.class, pokemons, this::onPokemonPicked);
    }

    private void onPokemonPicked(ObservableValue<?> _obs, Pokemon oldPokemon, Pokemon newPokemon) {
        builder.setTargetPokemon(newPokemon);
        executeGameMove();
    }

}
