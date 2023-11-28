package org.fiuba.algoritmos3.models.event.listener;

import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.event.listener.StatusListener;
import org.fiuba.algoritmos3.model.event.listener.WeatherListener;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.weather.Weather;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

public class ListenerTest {
    @Test
    public void testOnEvent() {
        GameState gameStateMock = Mockito.mock(GameState.class);
        Weather weatherMock = Mockito.mock(Weather.class);

        WeatherListener weatherListener = new WeatherListener(gameStateMock);
        when(gameStateMock.getWeather()).thenReturn(weatherMock);

        weatherListener.onEvent();
        verify(weatherMock).applyTo(gameStateMock);
    }

    @Test
    public void testOnEvent2() {

        GameState gameStateMock = Mockito.mock(GameState.class);
        Player currentPlayerMock = Mockito.mock(Player.class);
        Player opponentPlayerMock = Mockito.mock(Player.class);
        Pokemon pokemonMock = Mockito.mock(Pokemon.class);

        when(gameStateMock.getCurrentPlayer()).thenReturn(currentPlayerMock);
        when(currentPlayerMock.getOpponent()).thenReturn(opponentPlayerMock);

        when(currentPlayerMock.getPokemons()).thenReturn(List.of(pokemonMock));

        StatusListener statusListener = new StatusListener(gameStateMock);


        statusListener.onEvent();

        verify(gameStateMock, times(2)).getCurrentPlayer();
        verify(currentPlayerMock).getOpponent();
        verify(currentPlayerMock).getPokemons();
        verify(pokemonMock).getStatuses();
    }
}
