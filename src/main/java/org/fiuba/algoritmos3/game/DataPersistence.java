package org.fiuba.algoritmos3.game;

import org.fiuba.algoritmos3.jsonManager.serializer.GameResultSerializer;
import org.fiuba.algoritmos3.jsonManager.serializer.PlayerSerializer;
import org.fiuba.algoritmos3.models.Player;

import java.util.HashMap;

public class DataPersistence {

    public static void savePlayersInfo(GameState gameState) {
        Player playerOne = gameState.getCurrentPlayer();
        Player playerTwo = gameState.getCurrentPlayer().getOpponent();

        // Players info into Hashmap
        HashMap<String, Player> players = new HashMap<>();
        players.put(playerOne.getName(), playerOne);
        players.put(playerTwo.getName(), playerTwo);

        // Save to json with serializer
        new PlayerSerializer(players);
    }

    public static void saveGameResult(GameState gameState) {
        // Players info into Hashmap
        HashMap<String, Player> players = new HashMap<>();
        players.put("winner", gameState.getWinner());
        players.put("loser", gameState.getWinner().getOpponent());

        // Save to json with serializer
        new GameResultSerializer(players);
    }


}
