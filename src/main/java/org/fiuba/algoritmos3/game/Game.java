package org.fiuba.algoritmos3.game;

import com.github.underscore.U;
import org.fiuba.algoritmos3.GameAPI;
import org.fiuba.algoritmos3.game.error.InvalidSelectionException;
import org.fiuba.algoritmos3.game.menu.Menu;
import org.fiuba.algoritmos3.game.menu.MenuItem;
import org.fiuba.algoritmos3.game.menu.operation.OperationResult;
import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.item.Item;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.game.model.pokemon.status.ApplyableStatus;
import org.fiuba.algoritmos3.game.model.weather.*;
import org.fiuba.algoritmos3.game.move.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.fiuba.algoritmos3.Constants.*;


public class Game implements GameAPI {

    private final HashMap<Integer, Item> sourceItemsHash;
    private final HashMap<Integer, Pokemon> sourcePokemonHash;
    private Menu<String> menu;
    private GameState gameState;

    public Game(HashMap<Integer, Item> itemsHash, HashMap<Integer, Pokemon> pokemonsHash) {
        this.sourceItemsHash = itemsHash;
        this.sourcePokemonHash = pokemonsHash;
    }


    public void start() throws IOException, InvalidSelectionException {
        ui.showMessage(">>>>>> Welcome to Battle Pokemon <<<<<<<");
        setup();

        //Save Initial State of players and info in JSON
        Persistence.savePlayersInfo(gameState);

        Player winner = play();

        //Save Game Result and Players' State in JSON
        Persistence.saveGameResult(gameState);
        //Show Game result in UI
        ui.showMessage(winner.getName() + " won");
    }

    private Player play() throws IOException, InvalidSelectionException {
        Player winner;
        do {
            OperationResult<String> result;
            do {
                result = menu.show(ui);
                if (result.isErr()) ui.showMessage(result.getError().getMessage());
            } while (result.isErr());

            ui.showMessage(result.getResult());

            applyStatusesAndWeather();
            gameState.swapPlayers();

            winner = gameState.getWinner();
        } while (winner == null);

        return winner;
    }


    private void applyStatusesAndWeather() {
        applyPokemonStatuses();
        applyWeather();
    }

    private void setup() throws IOException, InvalidSelectionException {
        Player player1 = generatePlayer();
        player1.setCurrentPokemon(PokemonChooser.chooseFirstPokemon(ui, player1));

        Player player2 = generatePlayer();
        player2.setCurrentPokemon(PokemonChooser.chooseFirstPokemon(ui, player2));

        gameState = new GameState(player1, player2);

        gameState.setWeather(Math.random() < 2.0 / 3.0 ? new NoneWeather() : U.sample(List.of(new FogWeather(),
                new HurricaneWeather(), new RainWeather(),
                new SandstormWeather(), new SunnyWeather(), new ThunderstormWeather())));

        menu = generateMenu(gameState);
    }


    private Menu<String> generateMenu(GameState gameState) {
        return new Menu<>(List.of(
                new MenuItem<>(UseSkill.label, new UseSkill(gameState)),
                new MenuItem<>(UseItem.label, new UseItem(gameState)),
                new MenuItem<>(ChangePokemon.label, new ChangePokemon(gameState)),
                new MenuItem<>(Surrender.label, new Surrender(gameState))
        ));
    }

    private Player generatePlayer() {
        String playerName;

        do {
            playerName = this.ui.askForText("Enter your name: ");
            if (playerName.length() > MAX_NAME_LEN) {
                ui.showMessage("Names can have up to 50 characters");
            }
        } while (playerName.length() > MAX_NAME_LEN);

        ArrayList<Item> itemsList = new ArrayList<>(U.sample(List.copyOf(sourceItemsHash.values()), INITIAL_ITEMS));


        List<Item> hyperPotions = itemsList.stream()
                .filter(item -> item.getName().equals("Hyper Potion"))
                .toList();

        if (hyperPotions.size() > 1) {
            hyperPotions.subList(1, hyperPotions.size()).clear(); //dejo por ejemplo el primer hyperpotion
        }

        itemsList.addAll(hyperPotions);

        return new Player(playerName, generatePokemonRoster(), itemsList);
    }


    private ArrayList<Pokemon> generatePokemonRoster() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        ArrayList<PokemonSpecies> existingSpecies = new ArrayList<>();

        for (int i = 0; i < POKEMON_ROSTER_SIZE; i++) {
            Pokemon pokemon;
            do {
                pokemon = randomPokemon();
            } while (existingSpecies.contains(pokemon.getSpecies()));

            existingSpecies.add(pokemon.getSpecies());
            pokemons.add(pokemon);
        }

        return pokemons;
    }


    public Pokemon randomPokemon() {
        List<Integer> pokemonIds = new ArrayList<>(sourcePokemonHash.keySet());

        if (!pokemonIds.isEmpty()) {
            int randomIndex = U.random(pokemonIds.size() - 1);

            Integer randomPokemonId = pokemonIds.get(randomIndex);
            return sourcePokemonHash.get(randomPokemonId);
        } else {
            return null;
        }
    }

    private void applyPokemonStatuses() {
        List<Player> players = List.of(
                gameState.getCurrentPlayer(),
                gameState.getCurrentPlayer().getOpponent()
        );

        players.forEach(player -> {
            List<Pokemon> pokemons = player.getPokemons();
            pokemons.forEach(pokemon -> pokemon.getStatuses().stream().filter(status ->
                    status instanceof ApplyableStatus).forEach(status -> ((ApplyableStatus) status).apply(pokemon)));
        });
    }

    private void applyWeather() {
        Weather weather = gameState.getWeather();
        if (weather instanceof DamagingWeather damagingWeather) {
            damagingWeather.endTurn(gameState);
        }
    }

}