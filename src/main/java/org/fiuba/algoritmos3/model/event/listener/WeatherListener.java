package org.fiuba.algoritmos3.model.event.listener;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.event.RoundOverEvent;
import org.fiuba.algoritmos3.model.weather.Weather;

public class WeatherListener implements GameEventListener<RoundOverEvent> {
    private final GameState gameState;

    public WeatherListener(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void onEvent() {
        Weather weather = gameState.getWeather();
        weather.applyTo(gameState);
    }
}
