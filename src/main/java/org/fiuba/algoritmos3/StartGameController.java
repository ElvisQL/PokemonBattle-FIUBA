package org.fiuba.algoritmos3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.fiuba.algoritmos3.errors.InvalidDataException;
import org.fiuba.algoritmos3.game.Game;
import org.fiuba.algoritmos3.jsonManager.deserializer.ItemDeserializer;
import org.fiuba.algoritmos3.jsonManager.deserializer.PokemonDeserializer;
import org.fiuba.algoritmos3.models.item.Item;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StartGameController {
    @FXML
    private Label title;
    @FXML
    private TextField text;
    private int player = 1;
    private Game game ;


    public void initialize() throws InvalidDataException {
        /*ItemDeserializer itemDeserializer = new ItemDeserializer();
        HashMap<Integer, Item> hashItems = itemDeserializer.getItems();
                                                                                            TODO hay que crear los respecticos json
        PokemonDeserializer pokemonDeserializer = new PokemonDeserializer();
        HashMap<Integer, Pokemon> pokemonHashMap = pokemonDeserializer.getPokemon();*/

        /*this.game = new Game(null,hashItems,pokemonHashMap);*/ //FIXME habria que quitar el UI ?
        this.title.setText("Por favor ingrese el nombre del jugador " + player + ":");
    }
    @FXML
    private void onEnterPressed(KeyEvent event){
        if (event.getCode().getName().equals("Enter")){
            String name = text.getText();
            //hay que crear los players aqui

            player++;

            if (player > 2){
                //hay que mostrar la siguiente view
            }
            else {
                title.setText("Por favor ingrese el nombre del jugador " + player + ":");
                text.clear();
            }
        }

    }
}
