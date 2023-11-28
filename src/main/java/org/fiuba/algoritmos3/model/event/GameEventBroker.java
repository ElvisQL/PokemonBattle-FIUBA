package org.fiuba.algoritmos3.model.event;

import org.fiuba.algoritmos3.model.event.listener.GameEventListener;

import java.util.ArrayList;
import java.util.List;

public class GameEventBroker<T extends GameEvent> {
    private final List<GameEventListener<T>> listeners = new ArrayList<>();

    public void fireEvent() {
        listeners.forEach(listener -> listener.onEvent());
    }

    public void addListener(GameEventListener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(GameEventListener<T> listener) {
        listeners.remove(listener);
    }
}
