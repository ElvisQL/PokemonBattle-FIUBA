package org.fiuba.algoritmos3.jsonManager;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class JsonManager {
    private final ObjectMapper objectMapper;
    private JsonNode jsonNode;

    public JsonManager() {
        this.objectMapper = new ObjectMapper();

    }

    public JsonManager Writer(String path, Object object) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(path), object);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }


    public JsonManager Reader(String path) {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//Programa no falla si no encuentra key exacta
            this.jsonNode = objectMapper.readTree(
                    new File(path)
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JsonNode getNode() {
        return jsonNode;
    }
}
