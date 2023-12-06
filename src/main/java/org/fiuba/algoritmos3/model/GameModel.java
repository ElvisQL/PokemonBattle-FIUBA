package org.fiuba.algoritmos3.model;

import com.github.underscore.U;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.event.GameEventBroker;
import org.fiuba.algoritmos3.model.event.RoundOverEvent;
import org.fiuba.algoritmos3.model.event.listener.StatusListener;
import org.fiuba.algoritmos3.model.event.listener.WeatherListener;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.move.GameMove;
import org.fiuba.algoritmos3.model.move.GameMoveResult;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.model.weather.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.fiuba.algoritmos3.Constants.*;

public class GameModel {
    private final int MAX_PLAYERS = 2;

    private final List<PokemonSpecies> pokemonSpecies;
    private final HashMap<Integer, Item> sourceItemsHash;
    private final HashMap<Integer, Pokemon> sourcePokemonHash;
    private final GameState gameState = new GameState();
    private final GameEventBroker<RoundOverEvent> roundOverBroker = new GameEventBroker<>();

    public GameModel(HashMap<Integer, Item> itemsHash, HashMap<Integer, Pokemon> pokemonsHash, List<PokemonSpecies> pokemonSpecies) {
        this.sourceItemsHash = itemsHash;
        this.sourcePokemonHash = pokemonsHash;
        this.pokemonSpecies = pokemonSpecies;

        roundOverBroker.addListener(new WeatherListener(gameState));
        roundOverBroker.addListener(new StatusListener(gameState));
        roundOverBroker.addListener(gameState::swapPlayers);
    }

    public void startGame() {
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

        Persistence.savePlayersInfo(gameState);
    }

    public <T extends GameMove> GameMoveResult<String> playMove(T gameMove) {
        GameMoveResult<String> result = gameMove.run(gameState);

        roundOverBroker.fireEvent();

        return result;
    }

    public void stopGame() {
        Persistence.saveGameResult(gameState);
    }

    public void clearPlayers() {
        gameState.getPlayers().clear();
    }

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

    public Player getCurrentPlayer() {
        return gameState.getCurrentPlayer();
    }

    public List<Player> getPlayersList() {
        return gameState.getPlayers();
    }

    public Player getWinnerPlayer() {
        return gameState.getWinner();
    }

    public Weather getCurrentWeather() {
        return gameState.getWeather();
    }

    private Player getFirstPlayer(List<Player> players) {
        return U.max(players, player -> player.getCurrentPokemon().getAttackSpeed());
    }

    private ArrayList<Pokemon> generatePokemonRoster() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        for (int i = 0; i < POKEMON_ROSTER_SIZE; i++) {
            Integer id = U.random(0,AVAILABLE_POKEMONS);

            pokemons.add(
                    new PokemonBuilder().
                            setID(sourcePokemonHash.get(id).getID())
                            .setSpecies(sourcePokemonHash.get(id).getSpecies())
                            .setRandomAttributes()
                            .setSkills(getClonedSkills(sourcePokemonHash.get(id).getSkills()))
                            .build()
            );
        }

        return pokemons;
    }

    private ArrayList<ConcreteSkill> getClonedSkills(List<ConcreteSkill> skills) {
        ArrayList<ConcreteSkill> clonedSkills = new ArrayList<ConcreteSkill>();

        for (int i=0; i<skills.size(); i++){
            ConcreteSkill temp = skills.get(i).clone();
            temp.setRemainingUses(U.random(1, MAX_SKILL_USAGE));
            clonedSkills.add(
                    temp
            );
        }

        return clonedSkills;

    }
}
