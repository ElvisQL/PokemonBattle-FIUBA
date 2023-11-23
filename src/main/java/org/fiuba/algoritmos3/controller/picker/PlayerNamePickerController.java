package org.fiuba.algoritmos3.controller.picker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerNamePickerController extends PickerController<String> {
    @FXML
    private Button nextButton;
    @FXML
    private Label playerNameTitle;
    @FXML
    private TextField playerNameText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerNameTitle.setText("Jugador " + (gameAPI.getPlayers().size() + 1));
        playerNameText.clear();
    }

    @FXML
    public void onKeyTyped(KeyEvent keyEvent) {
        int nameLength = playerNameText.getText().length();
        boolean nameLengthWithinBounds = 0 < nameLength && nameLength < 50;
        nextButton.setDisable(!nameLengthWithinBounds);
    }

    @FXML
    private void onNextClick(MouseEvent event) {
        selection.setValue(playerNameText.getText());
    }
}
