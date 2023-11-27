package org.fiuba.algoritmos3.controller.picker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
    public BaseButton okButton;
    @FXML
    private ScrollPane scrollItems;
    @FXML
    private VBox itemsBox;
    @FXML
    private TextFlow descriptionBox;
    private String lastDescription = "";
    private HBox markedItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setDisable(true);
    }

    @Override
    public void setOptions(List<Item> options) {
        super.setOptions(options);
        setItems(options);
    }

    private void handleMouseClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        Item item = (Item) source.getUserData();
        if(markedItem != null){
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
        if(source instanceof HBox hBox && hBox != markedItem){
            source.getStyleClass().clear();
            source.getStyleClass().add("item-container");
            Text description = new Text(lastDescription);
            description.getStyleClass().add("label-description");
            descriptionBox.getChildren().clear();
            descriptionBox.getChildren().add(description);
        }

    }

    private void handleMouseEntered(MouseEvent e){

        Node source = (Node) e.getSource();
        if(source instanceof HBox hBox && hBox!=markedItem){
            source.getStyleClass().add("item-mouse-entered");

            Item item = (Item) source.getUserData();

            Text description = new Text(item.getDescription());
            description.getStyleClass().add("label-description");
            descriptionBox.getChildren().setAll(description);

            lastDescription = item.getDescription();
        }


    }
    private void setItems(List<Item> items) {

        Map<Integer, Integer> itemCountMap = new HashMap<>();

        for (Item item : items) {
            itemCountMap.put(item.getId(), itemCountMap.getOrDefault(item.getId(), 0) + 1);
        }


        for (Map.Entry<Integer, Integer> entry : itemCountMap.entrySet()) {
            int itemId = entry.getKey();
            int quantity = entry.getValue();

            HBox pane = new HBox(300);

            Item item = items.stream().filter(i -> i.getId() == itemId).findFirst().orElse(null);
            if (item != null){
                Label nameLabel = new Label(item.getName());
                Label quantityLabel = new Label("x" + quantity);
                nameLabel.setStyle("-fx-font-size: 28px;");
                quantityLabel.setStyle("-fx-font-size: 28px;");

                nameLabel.getStyleClass().add("label-item");
                quantityLabel.getStyleClass().add("label-item");

                HBox.setHgrow(nameLabel, Priority.ALWAYS);
                pane.getChildren().addAll(nameLabel, quantityLabel);
                pane.setAlignment(Pos.CENTER);

                pane.setUserData(item);

                pane.setOnMouseEntered(this::handleMouseEntered);
                pane.setOnMouseExited(this::handleMouseExited);
                pane.setOnMouseClicked(this::handleMouseClicked);

                pane.getStyleClass().add("item-container");
                itemsBox.getChildren().add(pane);
            }


        }
        scrollItems.setContent(itemsBox);

    }


    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            changeScene(event, getResource("views/chooseGameMove/choose-game-move-view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleOkButton(ActionEvent event) {
        Node source = (Node) event.getSource();
        selection.setValue((Item) source.getUserData());
    }
}
