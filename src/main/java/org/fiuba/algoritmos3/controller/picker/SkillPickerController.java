package org.fiuba.algoritmos3.controller.picker;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.Skill;

import java.net.URL;
import java.util.ResourceBundle;

public class SkillPickerController extends PickerController<ConcreteSkill> {
    @FXML
    private VBox itemsContainer;
    @FXML
    private TextFlow descriptionBox;
    @FXML
    private String lastDescription = "";
    @FXML
    private HBox markedItem;

    private ConcreteSkill currentSkill;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setDisable(true);
        okButton.setOnMouseClicked(this::handleOkButtonClick);
        backButton.setOnMouseClicked(this::handleBackButtonClick);
    }

    private void handleMouseClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        currentSkill = (ConcreteSkill) source.getUserData();
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

            ConcreteSkill item = (ConcreteSkill) source.getUserData();

            Text description = new Text(item.getDescription());
            description.getStyleClass().add("label-description");
            descriptionBox.getChildren().setAll(description);

            lastDescription = item.getDescription();
        }
    }


    protected void updateView() {
        for (ConcreteSkill skill : getOptions()) {
            HBox itemBox = new HBox(230);

            Label nameLabel = new Label("   " + skill.getName().toUpperCase());

            nameLabel.getStyleClass().add("label-item");

            itemBox.getChildren().addAll(nameLabel);

            itemBox.setUserData(skill);

            itemBox.setOnMouseEntered(this::handleMouseEntered);
            itemBox.setOnMouseExited(this::handleMouseExited);
            itemBox.setOnMouseClicked(this::handleMouseClicked);

            itemBox.getStyleClass().add("item-container");
            itemsContainer.getChildren().add(itemBox);
        }
    }


    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        back.setValue(true);
    }


    @FXML
    private void handleOkButtonClick(MouseEvent event) {
        selection.setValue(currentSkill);
    }
}
