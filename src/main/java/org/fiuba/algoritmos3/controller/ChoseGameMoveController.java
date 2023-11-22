package org.fiuba.algoritmos3.controller;

import javafx.scene.control.SplitPane;
import org.fiuba.algoritmos3.view.PokemonBattlefieldView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoseGameMoveController extends BaseController {
    public SplitPane pokemonsSplitPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PokemonBattlefieldView currentPokemonView = new PokemonBattlefieldView(gameAPI.currentPlayer().getCurrentPokemon());
        pokemonsSplitPane.getItems().add(currentPokemonView);

        PokemonBattlefieldView opponentPokemonView = new PokemonBattlefieldView(gameAPI.currentPlayer().getOpponent().getCurrentPokemon());
        opponentPokemonView.setFlipped(true);
        pokemonsSplitPane.getItems().add(opponentPokemonView);
    }
}
