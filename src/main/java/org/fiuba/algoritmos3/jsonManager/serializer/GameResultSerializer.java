package org.fiuba.algoritmos3.jsonManager.serializer;

import org.fiuba.algoritmos3.game.GameOverStateData;
import org.fiuba.algoritmos3.jsonManager.JsonManager;
import org.fiuba.algoritmos3.models.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GameResultSerializer {

    public GameResultSerializer(HashMap<String, Player> players) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = sdf.format(new Date()) + "-summary.json";

        new JsonManager().Writer(
                fileName,
                cleanData(players));
    }

    private List<GameOverStateData> cleanData(HashMap<String, Player> players) {

        List<GameOverStateData> playersData = new ArrayList<>();

        playersData.add(
                new GameOverStateData().buildPlayerGameOverState(players.get("winner")).setAsWinner(true));
        playersData.add(
                new GameOverStateData().buildPlayerGameOverState(players.get("loser")).setAsWinner(false));

        return playersData;
    }
}
