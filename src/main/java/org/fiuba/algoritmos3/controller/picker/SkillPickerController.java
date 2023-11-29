package org.fiuba.algoritmos3.controller.picker;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SkillPickerController extends PickerController<ConcreteSkill> {
    @FXML
    public VBox skillsContainer;

    @FXML
    private TextFlow skillDescriptionBox;
    @FXML
    private String lastDescription = "";
    @FXML
    private HBox markedItem;
    private ConcreteSkill currentSkill;

    @FXML
    public ImageView pokemon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setDisable(true);
        okButton.setOnMouseClicked(this::handleOkButtonClick);
        backButton.setOnMouseClicked(this::handleBackButtonClick);
        skillDescriptionBox.getChildren().add(new Text("What will we use?"));

        URL imageUrl = Objects.requireNonNull(getResource("images/pokemon/" + gameAPI.currentPlayer().getCurrentPokemon().getName().toLowerCase() + ".png"));
        Image image = new Image(imageUrl.toExternalForm(), pokemon.getFitWidth(), pokemon.getFitHeight(), true, false);
        pokemon.setImage(image);
    }

    private void handleMouseClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        currentSkill = (ConcreteSkill) source.getUserData();
        if (markedItem != null) {
            if (markedItem.equals(source)) {
                markedItem.getStyleClass().remove("marked-skill");
                markedItem.setOnMouseEntered(this::handleMouseEntered);
                markedItem.setOnMouseExited(this::handleMouseExited);
                markedItem = null;
                okButton.setDisable(true);
                return;
            } else {

                markedItem.getStyleClass().remove("marked-skill");
                source.getStyleClass().add("skill-container");
                markedItem.setOnMouseEntered(this::handleMouseEntered);
                markedItem.setOnMouseExited(this::handleMouseExited);
            }
        }
        source.getStyleClass().add("marked-skill");
        source.setOnMouseEntered(null);
        source.setOnMouseExited(null);
        markedItem = (HBox) source;
        okButton.setDisable(false);

    }

    private void handleMouseExited(MouseEvent e) {
        Node source = (Node) e.getSource();
        if (source instanceof HBox hBox && hBox != markedItem) {
            source.getStyleClass().clear();
            source.getStyleClass().add("skill-container");
            Text description = new Text(lastDescription);
            description.getStyleClass().add("skill-label-description");
            skillDescriptionBox.getChildren().clear();
            skillDescriptionBox.getChildren().add(description);
        }
    }

    private void handleMouseEntered(MouseEvent e) {
        Node source = (Node) e.getSource();
        if (source instanceof HBox hBox && hBox != markedItem) {
            source.getStyleClass().add("skill-mouse-entered");

            ConcreteSkill item = (ConcreteSkill) source.getUserData();

            Text description = new Text(item.getDescription());
            description.getStyleClass().add("label-description");
            skillDescriptionBox.getChildren().setAll(description);

            lastDescription = item.getDescription();
        }
    }


    protected void updateView() {
        for (ConcreteSkill skill : getOptions()) {
            HBox skillBox = new HBox(230);

            Label nameLabel = new Label("   " + skill.getName().toUpperCase());
            Label quantityLabel = new Label("x" + skill.getRemainingUses().toString());

            nameLabel.getStyleClass().add("label-item");
            quantityLabel.getStyleClass().add("label-item");

            skillBox.getChildren().addAll(nameLabel, quantityLabel);

            skillBox.setUserData(skill);

            skillBox.setOnMouseEntered(this::handleMouseEntered);
            skillBox.setOnMouseExited(this::handleMouseExited);
            skillBox.setOnMouseClicked(this::handleMouseClicked);

            skillBox.getStyleClass().add("skill-container");
            skillsContainer.getChildren().add(skillBox);
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
