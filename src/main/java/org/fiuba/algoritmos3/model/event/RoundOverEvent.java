package org.fiuba.algoritmos3.model.event;

public class RoundOverEvent extends GameEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public RoundOverEvent(Object source) {
        super(source);
    }
}
