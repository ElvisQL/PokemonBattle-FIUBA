package org.fiuba.algoritmos3.game.model.pokemon.skills;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EfficacyTableLoader {
    private static final String JSON_FILE_PATH = "src/resources/efficacyTable.json";
    private static final HashMap<PokemonType, HashMap<PokemonType, Double>> efficacyHash = new HashMap<>();

    public static HashMap<PokemonType, HashMap<PokemonType, Double>> load() throws IOException {
        if (!efficacyHash.isEmpty()) {
            return efficacyHash;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, HashMap<String, Double>> data = objectMapper.readValue(
                new File(JSON_FILE_PATH),
                new TypeReference<>() {
                }
        );

        for (HashMap.Entry<String, HashMap<String, Double>> entry : data.entrySet()) {
            PokemonType attackerType = PokemonType.valueOf(entry.getKey());
            HashMap<String, Double> innerMap = entry.getValue();
            HashMap<PokemonType, Double> secondHash = new HashMap<>();

            for (HashMap.Entry<String, Double> innerEntry : innerMap.entrySet()) {
                PokemonType targetType = PokemonType.valueOf(innerEntry.getKey());
                Double value = innerEntry.getValue();
                secondHash.put(targetType, value);
            }

            efficacyHash.put(attackerType, secondHash);
        }
        return efficacyHash;
    }
}

