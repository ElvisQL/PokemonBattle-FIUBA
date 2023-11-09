package org.fiuba.algoritmos3.jsonManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonManagerTest {
    ObjectNode jsonWrite;
    String path = "test.json";

    @BeforeEach
    public void setUp() {
        // Create test json object to test
        ObjectMapper objectMapper = new ObjectMapper();
        jsonWrite = objectMapper.createObjectNode();
        jsonWrite.put("name", "John");
    }

    @Test
    void writer() {
        // write with jsonManager class
        new JsonManager().Writer(path, jsonWrite);

        // Verify that the file exists
        Path filePath = new File(path).toPath();
        Assertions.assertTrue(Files.exists(filePath));
    }

    @Test
    void reader() {
        // read with jsonManager class
        JsonNode node = new JsonManager().Reader("src/test/java/org/fiuba/algoritmos3/testRead.json").getNode();

        // Verify that the file exists
        assertNotNull(node);
        Assertions.assertEquals(jsonWrite.get("name"), node.get("name"));
    }

    @Test
    void getNode() {
    }
}