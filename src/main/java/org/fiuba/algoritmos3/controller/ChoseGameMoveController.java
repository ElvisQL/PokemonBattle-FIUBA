package org.fiuba.algoritmos3.controller;

import javafx.scene.layout.VBox;
import org.fiuba.algoritmos3.view.chooseGameMove.PokemonView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoseGameMoveController extends BaseController {
    public VBox pokemonsSplitPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PokemonView opponentPokemonView = new PokemonView(gameAPI.currentPlayer().getOpponent().getCurrentPokemon());
        opponentPokemonView.setFlipped(true);
        pokemonsSplitPane.getChildren().add(opponentPokemonView);

        PokemonView currentPokemonView = new PokemonView(gameAPI.currentPlayer().getCurrentPokemon());
        pokemonsSplitPane.getChildren().add(currentPokemonView);
    }
}
