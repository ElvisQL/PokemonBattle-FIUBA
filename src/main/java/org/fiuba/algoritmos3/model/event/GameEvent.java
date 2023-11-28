package org.fiuba.algoritmos3.model.event;

import org.fiuba.algoritmos3.model.event.listener.GameEventListener;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public abstract class GameEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameEvent(Object source) {
        super(source);
    }
}
