package org.fiuba.algoritmos3.game;

import com.github.underscore.U;
import org.fiuba.algoritmos3.GameAPI;
import org.fiuba.algoritmos3.game.menu.Menu;
import org.fiuba.algoritmos3.game.menu.MenuItem;
import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.item.Item;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.game.model.pokemon.status.ApplyableStatus;
import org.fiuba.algoritmos3.game.model.weather.*;
import org.fiuba.algoritmos3.game.move.ChangePokemon;
import org.fiuba.algoritmos3.game.move.Surrender;
import org.fiuba.algoritmos3.game.move.UseItem;
import org.fiuba.algoritmos3.game.move.UseSkill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.fiuba.algoritmos3.Constants.*;


public class Game implements GameAPI {

    private final HashMap<Integer, Item> sourceItemsHash;
    private final HashMap<Integer, Pokemon> sourcePokemonHash;
    private final GameState gameState = new GameState();

    public Game(HashMap<Integer, Item> itemsHash, HashMap<Integer, Pokemon> pokemonsHash) {
        this.sourceItemsHash = itemsHash;
        this.sourcePokemonHash = pokemonsHash;
    }

    public void start() {
        gameState.setWeather(Math.random() < 2.0 / 3.0 ? new NoneWeather() : U.sample(List.of(new FogWeather(),
                new HurricaneWeather(), new RainWeather(),
                new SandstormWeather(), new SunnyWeather(), new ThunderstormWeather())));

        //Save Initial State of players and info in JSON
        Persistence.savePlayersInfo(gameState);
    }

    public void stop() {
        //Save Game Result and Players' State in JSON
        Persistence.saveGameResult(gameState);
    }

    // TODO esto lo tenemos que ir separando en los respectivos controllers
    private void play() {
//        Player winner;
//        do {
//            OperationResult<String> result;
//            do {
//                result = menu.show(ui);
//                if (result.isErr()) ui.showMessage(result.getError().getMessage());
//            } while (result.isErr());
//
//            ui.showMessage(result.getResult());
//
//            applyStatusesAndWeather();
//            gameState.swapPlayers();
//
//            winner = gameState.getWinner();
//        } while (winner == null);
//
//        return winner;
    }


    private void applyStatusesAndWeather() {
        applyPokemonStatuses();
        applyWeather();
    }

    private Menu<String> generateMenu(GameState gameState) {
        return new Menu<>(List.of(
                new MenuItem<>(UseSkill.label, new UseSkill(gameState)),
                new MenuItem<>(UseItem.label, new UseItem(gameState)),
                new MenuItem<>(ChangePokemon.label, new ChangePokemon(gameState)),
                new MenuItem<>(Surrender.label, new Surrender(gameState))
        ));
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

    @Override
    public void createPlayer(String playerName) {
        if (playerName.length() > MAX_NAME_LEN) {
            throw new IllegalArgumentException("Names can have up to " + MAX_NAME_LEN + " characters");
        }

        ArrayList<Item> itemsList = new ArrayList<>(U.sample(List.copyOf(sourceItemsHash.values()), INITIAL_ITEMS));

        // TODO que pingo es este hardcodeo?
        List<Item> hyperPotions = itemsList.stream()
                .filter(item -> item.getName().equals("Hyper Potion"))
                .toList();

        if (hyperPotions.size() > 1) {
            hyperPotions.subList(1, hyperPotions.size()).clear(); //dejo por ejemplo el primer hyperpotion
        }

        itemsList.addAll(hyperPotions);

        Player player = new Player(playerName, generatePokemonRoster(), itemsList);
        gameState.addPlayer(player);
    }
}