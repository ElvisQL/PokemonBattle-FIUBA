package org.fiuba.algoritmos3.jsonManager.serializer;

import org.fiuba.algoritmos3.jsonManager.JsonManager;
import org.fiuba.algoritmos3.jsonManager.JsonPath;
import org.fiuba.algoritmos3.game.model.Player;
import org.fiuba.algoritmos3.game.model.PlayerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerSerializer {

    public PlayerSerializer(HashMap<String, Player> players) {
        new JsonManager().Writer(
                JsonPath.PLAYERS.toString(),
                cleanData(players));
    }

    private List<PlayerData> cleanData(HashMap<String, Player> players) {

        List<PlayerData> playersData = new ArrayList<>();
        players.forEach(
                (player, info) -> playersData.add(new PlayerData().buildFromActivePlayer(info))
        );

        return playersData;
    }
}
