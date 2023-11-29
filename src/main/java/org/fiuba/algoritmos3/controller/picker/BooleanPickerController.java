package org.fiuba.algoritmos3.controller.picker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BooleanPickerController extends PickerController<Boolean> {

    @FXML
    private Label questionTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionTitle.setText("Player " + (gameAPI.currentPlayer().getName()));
        questionTitle.setStyle("-fx-wrap-text: true;");
    }

    public void setQuestion(String question) {
        this.questionTitle.setText(question);
    }

    @FXML
    private void onNextClick(MouseEvent event) {
        selection.setValue(true);
    }

    @FXML
    private void onBackClick(MouseEvent event) {
        back.setValue(true);
    }
}
