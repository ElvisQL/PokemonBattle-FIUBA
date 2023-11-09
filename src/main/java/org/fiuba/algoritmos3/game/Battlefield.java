package org.fiuba.algoritmos3.game;

import com.github.underscore.U;
import org.fiuba.algoritmos3.UiDisplayableVisitor;
import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.models.Player;
import org.fiuba.algoritmos3.models.pokemon.Pokemon;
import org.fiuba.algoritmos3.models.weather.Weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class Battlefield {

    private final UserInterface ui;
    UiDisplayableVisitor visitor = new UiDisplayableVisitor();

    public Battlefield(UserInterface ui) {
        this.ui = ui;
    }

    public void display(GameState gameState) throws IOException {
        Player currentPlayer = gameState.getCurrentPlayer();
        Pokemon currentPokemon = currentPlayer.getCurrentPokemon();
        Pokemon otherPokemon = currentPlayer.getOpponent().getCurrentPokemon();

        Integer terminalWidth = 100;
        Integer terminalHeight = 15;


        Integer paddedTerminalWidth = terminalWidth - 2;
        Integer paddedTerminalHeight = terminalHeight - 2;

        // Top frame
        printFramed("-".repeat(paddedTerminalWidth), "*");
        printFramed(padRight("Current player: " + currentPlayer.getName(), paddedTerminalWidth), "|");

        printSpaces(1, paddedTerminalWidth);

        printFramed(padLeft(otherPokemon.getName(), paddedTerminalWidth), "|");
        printFramed(padLeft("HP: " + otherPokemon.getHealth().toString(), paddedTerminalWidth), "|");

        printSpaces(paddedTerminalHeight - 6, paddedTerminalWidth);

        printFramed(padRight(currentPokemon.getName(), paddedTerminalWidth), "|");
        printFramed(padRight("HP: " + currentPokemon.getHealth().toString(), paddedTerminalWidth), "|");

        // Bottom frame
        printFramed("-".repeat(paddedTerminalWidth), "*");
    }

    public void displayDetailed(Player player, Player otherPlayer, Weather weather) throws IOException {

        Integer terminalWidth = ui.getTerminalWidth();
        Integer terminalHeight = ui.getTerminalHeight();

        // Default values
        if (terminalWidth == 0 || terminalHeight == 0) {
            terminalWidth = 150;
            terminalHeight = 15;
        }

        Integer paddedTerminalWidth = terminalWidth - 2;
        Integer paddedTerminalHeight = terminalHeight - 2;

        // Top frame
        printFramed("-".repeat(paddedTerminalWidth), "*");

        printFramed(padRight("Weather: " + weather.getName(), paddedTerminalWidth), "|");
        printSpaces(1, paddedTerminalWidth);
        printFramed(padRight("Current player: " + player.getName(), paddedTerminalWidth), "|"); // 1


        printSpaces(1, paddedTerminalWidth); // 1

        printFramed(padRight("Enemy Pokemon:", paddedTerminalWidth), "|"); // 1
        printPokemonsDetails(otherPlayer.getPokemons(), paddedTerminalWidth); // 2

        printSpaces(paddedTerminalHeight - 8, paddedTerminalWidth);

        printFramed(padRight("Your Pokemon:", paddedTerminalWidth), "|"); // 1
        printPokemonsDetails(player.getPokemons(), paddedTerminalWidth); // 2

        // Bottom frame
        printFramed("-".repeat(paddedTerminalWidth), "*");
    }

    private void printPokemonsDetails(Collection<Pokemon> pokemons, Integer maxWidth) {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> hps = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            pokemon.accept(visitor);
            //String titleStr = pokemon.getItemText();
            String titleStr = visitor.getItemText();
            String hpStr = "HP: " + pokemon.getHealth().toString();

            Integer blockWidth = titleStr.length();
            if (hpStr.length() > blockWidth) blockWidth = hpStr.length();

            titles.add(padRight(titleStr, blockWidth));
            hps.add(padRight(hpStr, blockWidth));
        }

        Integer availableSpacing = maxWidth - U.join(titles, "").length();
        Integer spacing = availableSpacing / pokemons.size();

        printFramed(padRight(U.join(titles, " ".repeat(spacing)), maxWidth), "|");
        printFramed(padRight(U.join(hps, " ".repeat(spacing)), maxWidth), "|");
    }

    private void printSpaces(Integer n, Integer maxWidth) {
        U.times(n, () -> printFramed(" ".repeat(maxWidth), "|"));
    }

    private void printFramed(String s, String frame) {
        ui.showMessage(frame + s + frame);
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    private static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
