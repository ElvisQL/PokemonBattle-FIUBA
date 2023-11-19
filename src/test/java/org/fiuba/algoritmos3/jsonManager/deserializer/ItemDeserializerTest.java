package org.fiuba.algoritmos3.jsonManager.deserializer;

import org.fiuba.algoritmos3.game.error.InvalidDataException;
import org.fiuba.algoritmos3.game.model.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemDeserializerTest {

    @Test
    void getItems() throws InvalidDataException {
        HashMap<Integer, Item> itemDeserializer = new ItemDeserializer().getItems();

        assertNotNull(itemDeserializer);
        Assertions.assertFalse(itemDeserializer.isEmpty());
        Assertions.assertEquals(itemDeserializer.get(10).getName(), "Student Ruiner");
    }
}