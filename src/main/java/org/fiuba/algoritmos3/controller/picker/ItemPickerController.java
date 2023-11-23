package org.fiuba.algoritmos3.controller.picker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.view.BaseButton;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ItemPickerController extends PickerController<Item> {
    @FXML
    public BaseButton backButton;
    @FXML
    private VBox itemsBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Pane descriptionBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.scrollPane = new ScrollPane();

        Player currentPlayer = gameAPI.currentPlayer();
        List<Item> items = currentPlayer.getItems();
        setItems(items);
    }

    private void setItems(List<Item> items) {

        Map<Integer, Integer> itemCountMap = new HashMap<>();

        for (Item item : items) {
            itemCountMap.put(item.getId(), itemCountMap.getOrDefault(item.getId(), 0) + 1);
        }


        for (Item item : items) {
            Pane pane = new Pane();
            HBox labelsContainer = new HBox();
            labelsContainer.setAlignment(Pos.CENTER_LEFT);
            Label nameLabel = new Label(item.getName());
            Label quantityLabel = new Label(itemCountMap.get(item.getId()).toString());

            HBox.setHgrow(nameLabel, Priority.ALWAYS);

            labelsContainer.getChildren().addAll(nameLabel, quantityLabel);

            pane.getChildren().add(labelsContainer);


            pane.setOnMouseEntered(event -> {
                Label description = new Label(item.getDescription());
                descriptionBox.getChildren().setAll(description);
            });


            pane.getStyleClass().add("item-container");
            itemsBox.getChildren().add(pane);

        }
        scrollPane.setContent(itemsBox);
    }


    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            changeScene(event, getResource("views/chooseGameMove/choose-game-move-view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
