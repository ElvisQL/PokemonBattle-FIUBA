package org.fiuba.algoritmos3;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class WinnerViewController {
    @FXML
    private ImageView buttonNewGame;


    @FXML
    public void onMouseHover(Event e){
        Pane sourcePane = (Pane) e.getSource();
        if(sourcePane.getChildren().get(0) == buttonNewGame){
            URL imageUrl = getClass().getResource("images/boton-menu-selected.png");
            buttonNewGame.setImage(new Image(imageUrl.toExternalForm()));

        }
    }
    @FXML
    public void onMouseExit(Event e){
        Pane sourcePane = (Pane) e.getSource();
        if (sourcePane.getChildren().get(0) == buttonNewGame){
            URL imageUrl = getClass().getResource("images/boton-menu-selected.png");
            buttonNewGame.setImage(new Image(imageUrl.toExternalForm()));
        }
    }

}
