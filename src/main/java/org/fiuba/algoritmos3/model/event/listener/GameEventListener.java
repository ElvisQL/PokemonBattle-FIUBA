package org.fiuba.algoritmos3.model.event.listener;

import org.fiuba.algoritmos3.model.event.GameEvent;

import java.util.EventListener;

public interface GameEventListener<T extends GameEvent> extends EventListener {
    void onEvent();
}
