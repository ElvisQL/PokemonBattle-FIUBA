package org.fiuba.algoritmos3.controller.picker;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fiuba.algoritmos3.controller.gameMove.ChangePokemonController;
import org.fiuba.algoritmos3.controller.gameMove.GameMoveController;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.view.component.PokemonPickerOption;

import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.List;
import java.util.ResourceBundle;

public class SkillPickerController extends PickerController<ConcreteSkill> {

    public VBox pokemonsSplitPane;
    public TextFlow skillLabel;
    @FXML
    public TextFlow skillDescription;
    @FXML
    private ImageView backGroundWeather;
    @FXML
    private GridPane skillsGrid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        Text msg = new Text("Choose an attack!");
        msg.getStyleClass().add("message-text-choose-game-move");

        skillDescription.getChildren().add(msg);

    }

    @Override
    public void updateView(){
        List<ConcreteSkill> skills = this.gameAPI.currentPlayer().getCurrentPokemon().getSkills();


        skillsGrid.getChildren().clear();
        getOptions().forEach((pokemon) -> {
            PokemonPickerOption option = new PokemonPickerOption();
            option.setPokemon(pokemon);
            option.setEnemy(enemyPokemons.contains(pokemon));
            option.setOnMouseEntered((e) -> updateDetailsPanel(pokemon));
            option.setOnMouseExited((e) -> updateDetailsPanel(currentPokemon));
            option.setOnMouseClicked(this::handleChoosePokemonMouseClick);

            pokemonOptionsParent.getChildren().add(option);
        });
    }



    // TODO refactor
    private void loadSkillPickerController(Event e, GameMoveController<?, ?> controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getResource("views/skill-picker.fxml"));
        controller.setPreviousController(this);
        fxmlLoader.setController(controller);

        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 768, 768);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        changeScene(e, scene);
    }


}
