package org.fiuba.algoritmos3.controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fiuba.algoritmos3.controller.picker.PickerController;
import org.fiuba.algoritmos3.controller.picker.PlayerNamePickerController;
import org.fiuba.algoritmos3.controller.picker.PokemonPickerController;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerSetupController extends PickerWrapperController {
    Player player;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PickerController<String> controller = loadPicker(PlayerNamePickerController.class, null);
        controller.addSelectionListener(this::onPlayerNamePicked);
    }

    private void onPlayerNamePicked(ObservableValue<?> _obs, String oldName, String newName) {
        player = gameAPI.createPlayer(newName);
        PickerController<Pokemon> controller = loadPicker(PokemonPickerController.class, player.getPokemons());
        controller.addSelectionListener(this::onPokemonPicked);
    }

    private void onPokemonPicked(ObservableValue<?> _obs, Pokemon oldPokemon, Pokemon newPokemon) {
        try {
            player.setCurrentPokemon(newPokemon);
        } catch (InvalidSelectionException e) {
            throw new RuntimeException(e);
        }

        Stage stage = (Stage) rootPane.getScene().getWindow();
        if (gameAPI.getPlayers().size() >= 2) {
            gameAPI.start();

            // show message
            changeScene(stage, getResource("views/trainersBattlefield/message-accept-button-box.fxml"));
        } else {
            BaseController controller = new PlayerSetupController();
            FXMLLoader fxmlLoader = new FXMLLoader(getResource("views/picker-wrapper.fxml"));
            controller.setPreviousController(this);
            fxmlLoader.setController(controller);

            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load(), 768, 768);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            stage.setScene(scene);
        }
    }
}
