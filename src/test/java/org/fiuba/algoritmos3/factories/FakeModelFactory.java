package org.fiuba.algoritmos3.factories;

import java.util.ArrayList;
import java.util.List;

public interface FakeModelFactory<T> {
    T create(Integer id);

    default List<T> createList(Integer size) {
        return createList(size, 0);
    }

    default List<T> createList(Integer size, Integer startingId) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(create(startingId + i));
        }
        return list;
    }
}
