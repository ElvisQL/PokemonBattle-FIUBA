package org.fiuba.algoritmos3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.view.chooseGameMove.PokemonView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoseGameMoveController extends BaseController {
    public VBox pokemonsSplitPane;
    @FXML
    private Button changePokemon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PokemonView opponentPokemonView = new PokemonView(gameAPI.currentPlayer().getOpponent().getCurrentPokemon());
        opponentPokemonView.setFlipped(true);
        pokemonsSplitPane.getChildren().add(opponentPokemonView);

        PokemonView currentPokemonView = new PokemonView(gameAPI.currentPlayer().getCurrentPokemon());
        pokemonsSplitPane.getChildren().add(currentPokemonView);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            changeScene(event, getResource("views/pokemon-choice.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonItems(ActionEvent event) throws IOException{
        try {
            changeScene(event,getResource("views/items-view.fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
