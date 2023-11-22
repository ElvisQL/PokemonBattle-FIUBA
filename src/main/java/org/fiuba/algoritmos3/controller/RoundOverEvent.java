package org.fiuba.algoritmos3.controller;

import javafx.event.Event;
import javafx.event.EventType;

public class RoundOverEvent extends Event {
    public static EventType<RoundOverEvent> ROUND_OVER_EVENT = new EventType<>("Round Over Event");

    public RoundOverEvent() {
        super(ROUND_OVER_EVENT);
    }
}
