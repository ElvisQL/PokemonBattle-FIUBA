package org.fiuba.algoritmos3.view.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fiuba.algoritmos3.PokemonApp;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.BuffSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.StatusSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.WeatherSkill;

import java.io.IOException;
import java.util.Objects;

public class SkillTypeIcon extends AnchorPane {
    @FXML
    private TextFlow typeName;

    @FXML
    Rectangle background;
    private String type;
    private String color;

    public SkillTypeIcon(String label){
        super();
        this.type=label;

        initialize();
    }

    private void initialize(){
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("views/components/skill-type.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setIconStyle(this.type);

    }

    public void setIconStyle(String label){


        // text
        typeName.getChildren().add(new Text(type.toUpperCase()));

        //background
        if (Objects.equals(label, "Attack")) { // TODO enum
            color ="#FCA08D";
        } else if (Objects.equals(label, "Buff")) {
            color = "#FFC6DA";
        } else if (Objects.equals(label,"Status")) {
            color = "#FFDE88";
        } else if (Objects.equals(label, "Weather")) {
            color = "#DCE875";
        } else {
            color = "#7F7F7F";
        }
        background.setFill(Color.web(color));



    }
}
