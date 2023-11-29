package org.fiuba.algoritmos3.controller.gameMove;

import javafx.beans.value.ObservableValue;
import org.fiuba.algoritmos3.controller.picker.ItemPickerController;
import org.fiuba.algoritmos3.controller.picker.PickerController;
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
    private Item selectedItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new UseItemBuilder();
        loadItemPicker();
    }

    private void loadItemPicker() {
        PickerController<Item> controller = loadPicker(ItemPickerController.class, gameAPI.currentPlayer().getItems());
        controller.addSelectionListener(this::onItemPicked);
        controller.addBackListener((_a, _b, _c) -> loadChooseGameMove());
    }

    private void onItemPicked(ObservableValue<?> _obs, Item oldItem, Item newItem) {
        builder.setItem(newItem);
        selectedItem = newItem;
        loadPokemonPicker();
    }

    private void loadPokemonPicker() {
        List<Pokemon> pokemons = Stream.concat(
                        gameAPI.currentPlayer().getPokemons().stream(),
                        gameAPI.currentPlayer().getOpponent().getPokemons().stream()
                )
                .filter((pokemon) -> selectedItem.canUse(pokemon))
                .toList();

        PickerController<Pokemon> controller = loadPicker(PokemonPickerController.class, pokemons);
        controller.addSelectionListener(this::onPokemonPicked);
        controller.addBackListener((_a, _b, _c) -> loadItemPicker());
    }

    private void onPokemonPicked(ObservableValue<?> _obs, Pokemon oldPokemon, Pokemon newPokemon) {
        builder.setTargetPokemon(newPokemon);
        executeGameMove();
    }

}
