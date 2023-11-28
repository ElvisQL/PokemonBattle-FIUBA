package org.fiuba.algoritmos3.jsonManager.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import org.fiuba.algoritmos3.jsonManager.JsonManager;
import org.fiuba.algoritmos3.jsonManager.JsonPath;
import org.fiuba.algoritmos3.model.error.InvalidDataException;
import org.fiuba.algoritmos3.model.item.*;

import java.util.HashMap;

public class ItemDeserializer {

    private HashMap<Integer, Item> items;
    private ItemManager itemManager;

    public ItemDeserializer() throws InvalidDataException {
        try {
            JsonNode jsonNode = new JsonManager()
                    .Reader(JsonPath.ITEMS.toString())
                    .getNode();
            this.items = this.use(jsonNode);
            this.itemManager = new ItemManager(items);

            itemManager.applyItemLimitations("Hyper Potion", 1);
            //Aqui se puede añadir mas...

        } catch (InvalidDataException e) {
            e.printStackTrace();
        }

    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    private Item createItem(JsonNode item) throws InvalidDataException {
        String cat = item.get("category").asText();
        String name = item.get("name").asText();
        String description = item.get("description").asText();
        Integer id = item.get("id").asInt();

        return switch (cat) {
            case "HealingFixed" -> new FixedHealingItem(id, name, description, item.get("HP").asInt());
            case "HealingPercentage" -> new PercentageHealingItem(id, name, description, item.get("HP").asInt());
            case "Revival" -> new ReviveItem(id, name, description, item.get("percentage").asInt());
            case "Status" -> new RestoreStatusItem(id, name, description);
            case "Attack" -> new IncreaseAttackItem(id, name, description, item.get("percentage").asInt());
            case "Defense" -> new IncreaseDefenseItem(id, name, description, item.get("percentage").asInt());
            case "Level" -> new LevelUpItem(id, name, description, item.get("increment").asInt());
            default -> throw new InvalidDataException("items.json");
        };
    }

    private HashMap<Integer, Item> use(JsonNode itemsNode) throws InvalidDataException {

        HashMap<Integer, Item> itemsHash = new HashMap<>();
        if (itemsNode != null && itemsNode.isArray()) {

            for (JsonNode item : itemsNode) {
                itemsHash.put(item.get("id").asInt(), createItem(item));
            }

            return itemsHash;
        } else {
            throw new InvalidDataException("items.json");
        }

    }
}
