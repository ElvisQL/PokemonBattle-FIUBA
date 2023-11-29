package org.fiuba.algoritmos3.model;

import com.github.underscore.U;
import org.fiuba.algoritmos3.GameAPI;
import org.fiuba.algoritmos3.model.event.GameEventBroker;
import org.fiuba.algoritmos3.model.event.RoundOverEvent;
import org.fiuba.algoritmos3.model.event.listener.StatusListener;
import org.fiuba.algoritmos3.model.event.listener.WeatherListener;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.move.GameMove;
import org.fiuba.algoritmos3.model.move.GameMoveResult;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.weather.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.fiuba.algoritmos3.Constants.*;


public class Game implements GameAPI {
    private final int MAX_PLAYERS = 2;

    private final HashMap<Integer, Item> sourceItemsHash;
    private final HashMap<Integer, Pokemon> sourcePokemonHash;
    private final GameState gameState = new GameState();
    private GameMoveResult<String> msg;
    private final GameEventBroker<RoundOverEvent> roundOverBroker = new GameEventBroker<>();

    public Game(HashMap<Integer, Item> itemsHash, HashMap<Integer, Pokemon> pokemonsHash) {
        this.sourceItemsHash = itemsHash;
        this.sourcePokemonHash = pokemonsHash;

        roundOverBroker.addListener(new WeatherListener(gameState));
        roundOverBroker.addListener(new StatusListener(gameState));
        roundOverBroker.addListener(gameState::swapPlayers);
    }

    @Override
    public void start() {
        List<Player> players = gameState.getPlayers();
        if (players.size() >= MAX_PLAYERS) {
            players.get(0).setOpponent(players.get(1));
            players.get(1).setOpponent(players.get(0));

            gameState.setCurrentPlayer(getFirstPlayer(players));
        }

        boolean hasWeather = Math.random() < 2.0 / 3.0;
        Weather weather;
        if (!hasWeather)
            weather = new NoneWeather();
        else
            weather = U.sample(List.of(
                    new FogWeather(),
                    new HurricaneWeather(),
                    new RainWeather(),
                    new SandstormWeather(),
                    new SunnyWeather(),
                    new ThunderstormWeather()
            ));
        gameState.setWeather(weather);

        // Save Initial State of players and info in JSON
        Persistence.savePlayersInfo(gameState);
    }

    @Override
    public <T extends GameMove> GameMoveResult<String> play(T gameMove) {
        GameMoveResult<String> result = gameMove.run(gameState);
        roundOverBroker.fireEvent();

        return result;
    }

    @Override
    public void stop() {
        // Save Game Result and Players' State in JSON
        Persistence.saveGameResult(gameState);
    }

    @Override
    public void clearPlayer() {
        gameState.getPlayers().clear();
    }

    @Override
    public Player createPlayer(String playerName) {
        if (gameState.getPlayers().size() >= MAX_PLAYERS) {
            throw new IllegalArgumentException("The max amount of players is " + MAX_PLAYERS);
        }
        if (playerName.length() > MAX_NAME_LEN) {
            throw new IllegalArgumentException("Names can have up to " + MAX_NAME_LEN + " characters");
        }

        ArrayList<Item> itemsList = new ArrayList<>(U.sample(List.copyOf(sourceItemsHash.values()), INITIAL_ITEMS));

        Player player = new Player(playerName, generatePokemonRoster(), itemsList);
        gameState.addPlayer(player);

        return player;
    }

    @Override
    public Player currentPlayer() {
        return gameState.getCurrentPlayer();
    }

    @Override
    public List<Player> getPlayers() {
        return gameState.getPlayers();
    }

    @Override
    public Player getWinner() {
        return gameState.getWinner();
    }

    @Override
    public Weather getWeather() {
        return gameState.getWeather();
    }

    private Player getFirstPlayer(List<Player> players) {
        return U.max(players, player -> player.getCurrentPokemon().getAttackSpeed());
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
}