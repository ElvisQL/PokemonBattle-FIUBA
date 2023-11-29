package org.fiuba.algoritmos3.controller.messages;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.fiuba.algoritmos3.controller.BattleMessages;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.view.battlefield.TrainerView;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class StartBattleTrainersControllers extends MessageDisplayController {
    @FXML
    private ImageView oppositeTrainer;
    @FXML
    private ImageView activeTrainer;
    @FXML
    private GridPane actPokeballGrid;
    @FXML
    private GridPane oppPokeballGrid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        BattleMessages[] enumValues = BattleMessages.values();
        Random random = new Random();
        int randomIndex = random.nextInt(enumValues.length);
        String str = gameAPI.currentPlayer().getOpponent().getName().toUpperCase() +": "+ enumValues[randomIndex].toString();

        TrainerView trainersView = new TrainerView(gameAPI.currentPlayer(), gameAPI.currentPlayer().getOpponent());
        customView.getChildren().add(trainersView);
        showMessage(str);
        loadMusic("audio/battleMusic.wav");
    }




}
