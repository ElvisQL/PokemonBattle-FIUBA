package org.fiuba.algoritmos3.model;

import org.fiuba.algoritmos3.jsonManager.serializer.GameResultSerializer;
import org.fiuba.algoritmos3.jsonManager.serializer.PlayerSerializer;

import java.util.HashMap;

public class Persistence {

    public static void savePlayersInfo(GameState gameState) {
        Player playerOne = gameState.getCurrentPlayer();

        if (playerOne != null) {
            Player playerTwo = playerOne.getOpponent();

            HashMap<String, Player> players = new HashMap<>();
            players.put(playerOne.getName(), playerOne);

            if (playerTwo != null) {
                players.put(playerTwo.getName(), playerTwo);
            }

            new PlayerSerializer(players);
        }
    }

    public static void saveGameResult(GameState gameState) {
        // Players info into Hashmap
        HashMap<String, Player> players = new HashMap<>();
        Player winner = gameState.getWinner();
        Player loser = (winner != null) ? winner.getOpponent() : null;
        players.put("winner", winner);
        players.put("loser", loser);

        // Save to json with serializer
        new GameResultSerializer(players);
    }


}
