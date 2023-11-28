package org.fiuba.algoritmos3.model.event;

import java.util.EventObject;

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
