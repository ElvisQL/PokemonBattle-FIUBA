package org.fiuba.algoritmos3.controller.picker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fiuba.algoritmos3.model.item.Item;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ItemPickerController extends PickerController<Item> {
    @FXML
    private VBox itemsContainer;
    @FXML
    private TextFlow descriptionBox;
    @FXML
    private String lastDescription = "";
    @FXML
    private HBox markedItem;

    private Item currentItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setDisable(true);
    }

    private void handleMouseClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        currentItem = (Item) source.getUserData();
        if (markedItem != null) {
            if (markedItem.equals(source)) {
                markedItem.getStyleClass().remove("marked-item");
                markedItem.setOnMouseEntered(this::handleMouseEntered);
                markedItem.setOnMouseExited(this::handleMouseExited);
                markedItem = null;
                okButton.setDisable(true);
                return;
            } else {

                markedItem.getStyleClass().remove("marked-item");
                source.getStyleClass().add("item-container");
                markedItem.setOnMouseEntered(this::handleMouseEntered);
                markedItem.setOnMouseExited(this::handleMouseExited);
            }
        }
        source.getStyleClass().add("marked-item");
        source.setOnMouseEntered(null);
        source.setOnMouseExited(null);
        markedItem = (HBox) source;
        okButton.setDisable(false);
    }

    private void handleMouseExited(MouseEvent e) {
        Node source = (Node) e.getSource();
        if (source instanceof HBox hBox && hBox != markedItem) {
            source.getStyleClass().clear();
            source.getStyleClass().add("item-container");
            Text description = new Text(lastDescription);
            description.getStyleClass().add("label-description");
            descriptionBox.getChildren().clear();
            descriptionBox.getChildren().add(description);
        }
    }

    private void handleMouseEntered(MouseEvent e) {
        Node source = (Node) e.getSource();
        if (source instanceof HBox hBox && hBox != markedItem) {
            source.getStyleClass().add("item-mouse-entered");

            Item item = (Item) source.getUserData();

            Text description = new Text(item.getDescription());
            description.getStyleClass().add("label-description");
            descriptionBox.getChildren().setAll(description);

            lastDescription = item.getDescription();
        }
    }


    protected void updateView() {
        List<Item> items = getOptions();
        Map<Integer, Integer> itemCountMap = new HashMap<>();

        for (Item item : items) {
            itemCountMap.put(item.getId(), itemCountMap.getOrDefault(item.getId(), 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : itemCountMap.entrySet()) {
            int itemId = entry.getKey();

            HBox itemBox = new HBox(230);

            Item item = items.stream().filter(i -> i.getId() == itemId).findFirst().orElse(null);
            if (item != null) {
                Label nameLabel = new Label("   " + item.getName().toUpperCase());
                Label quantityLabel = new Label("x" + itemCountMap.get(item.getId()).toString());

                nameLabel.getStyleClass().add("label-item");
                quantityLabel.getStyleClass().add("label-item");

                itemBox.getChildren().addAll(nameLabel, quantityLabel);

                itemBox.setUserData(item);

                itemBox.setOnMouseEntered(this::handleMouseEntered);
                itemBox.setOnMouseExited(this::handleMouseExited);
                itemBox.setOnMouseClicked(this::handleMouseClicked);

                itemBox.getStyleClass().add("item-container");
                itemsContainer.getChildren().add(itemBox);
            }
        }
    }


    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        back.setValue(true);
    }

    public void handleOkButton(ActionEvent event) {
        selection.setValue(currentItem);
    }
}
