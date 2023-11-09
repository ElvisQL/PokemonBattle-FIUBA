package org.fiuba.algoritmos3;

import java.util.Collection;
import java.util.List;

public class MockUI implements UserInterface {

    @Override
    public void clearScreen() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public Boolean askForConfirmation(String msg) {
        return null;
    }

    @Override
    public String askForText(String msg) {
        return null;
    }

    @Override
    public <T extends UIDisplayable> T chooseOption(String promptText, Collection<T> items) {
        return null;
    }

    @Override
    public <T> T chooseOption(String promptText, Collection<T> items, List<String> itemTexts) {
        return null;
    }

    @Override
    public Integer getTerminalWidth() {
        return null;
    }

    @Override
    public Integer getTerminalHeight() {
        return null;
    }
}
