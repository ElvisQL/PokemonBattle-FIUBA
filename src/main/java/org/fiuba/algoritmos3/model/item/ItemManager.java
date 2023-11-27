package org.fiuba.algoritmos3.model.item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ItemManager {

    private HashMap<Integer, Item> items;
    private Map<String, Integer> limitedItemLimits;

    public ItemManager(HashMap<Integer, Item> items) {
        this.items = items;
        this.limitedItemLimits = new HashMap<>();
    }

    public void addItem(Integer itemId, Item newItem) {
        items.put(itemId, newItem);
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    public void applyItemLimitations(String itemToLimit, int limit) {

        limitedItemLimits.put(itemToLimit, limit);
        // Se filtra el HashMap para aplicar limitaciones
        items = filterItems(item -> {
            String itemName = item.getName();
            int itemLimit = limitedItemLimits.getOrDefault(itemName, Integer.MAX_VALUE);
            return getCurrentItemCount(itemName) < itemLimit;
        });
    }

    private int getCurrentItemCount(String itemName) {
        int count = 0;
        for (Item item : items.values()) {
            if (item.getName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    private HashMap<Integer, Item> filterItems(Predicate<Item> filter) {
        // Filtro el HashMap usando la condición dada por el predicado
        HashMap<Integer, Item> filteredItems = new HashMap<>();
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            if (filter.test(entry.getValue())) {
                filteredItems.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredItems;
    }

}