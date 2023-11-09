package org.fiuba.algoritmos3;


import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.*;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class TerminalUI implements UserInterface {

    public TerminalUI() {
        AnsiConsole.systemInstall();
    }

    public void clearScreen() {
        System.out.println(ansi().eraseScreen());
    }


    /**
     * showMessage
     *
     * @param msg Text to be displayed
     */
    public void showMessage(String msg) {
        System.out.println(
                ansi().render(msg)
        );
    }

    /**
     * askForConfirmation
     *
     * @param msg Text to be displayed
     * @return boolean representing user confirmation
     * @throws IOException In case of error
     */
    public Boolean askForConfirmation(String msg) throws IOException {

        ConsolePrompt prompt = new ConsolePrompt();
        PromptBuilder promptBuilder = prompt
                .getPromptBuilder()
                .createConfirmPromp()
                .name("value")
                .message(msg)
                .defaultValue(ConfirmChoice.ConfirmationValue.YES)
                .addPrompt();

        HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
        return ((ConfirmResult) result.get("value")).getConfirmed() == ConfirmChoice.ConfirmationValue.YES;
    }

    /**
     * askForText
     *
     * @param msg Text to be displayed
     * @return String inputted by the user
     * @throws IOException In case of error
     */
    public String askForText(String msg) throws IOException {
        ConsolePrompt prompt = new ConsolePrompt();
        PromptBuilder promptBuilder = prompt
                .getPromptBuilder()
                .createInputPrompt()
                .name("value")
                .message(msg)
                .addPrompt();

        HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());

        return ((InputResult) result.get("value")).getInput();
    }


    /**
     * chooseOption
     *
     * @param promptText Text to be displayed
     * @param items      List of displayable options
     * @return ID of the selected option
     * @throws IOException In case something gets f*cked up
     */


    public <T extends UIDisplayable> T chooseOption(String promptText, Collection<T> items) throws IOException {
        ConsolePrompt prompt = new ConsolePrompt();
        PromptBuilder promptBuilder = prompt.getPromptBuilder();
        ListPromptBuilder listBuilder = promptBuilder
                .createListPrompt()
                .name("value")
                .message(promptText);

        items.forEach((item) -> listBuilder.newItem(item.getItemId()).text(item.getItemText()).add());

        HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(listBuilder.addPrompt().build());

        String id = ((ListResult) result.get("value")).getSelectedId();

        for (T item : items) {
            if (item.getItemId().equals(id)) {
                return item;
            }
        }

        return null;
    }

    public <T> T chooseOption(String promptText, Collection<T> items, List<String> itemTexts) throws IOException {
        ConsolePrompt prompt = new ConsolePrompt();
        PromptBuilder promptBuilder = prompt.getPromptBuilder();
        ListPromptBuilder listBuilder = promptBuilder
                .createListPrompt()
                .name("value")
                .message(promptText);

        int itemId = 0;
        for (String itemText : itemTexts) {
            listBuilder.newItem(String.valueOf(itemId)).text(itemText).add();
            itemId++;
        }

        HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(listBuilder.addPrompt().build());

        String selectedId = ((ListResult) result.get("value")).getSelectedId();

        int selectedIndex = Integer.parseInt(selectedId);

        if (selectedIndex >= 0 && selectedIndex < items.size()) {
            return items.stream()
                    .skip(selectedIndex)
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }


    public Integer getTerminalWidth() throws IOException {
        return org.jline.terminal.TerminalBuilder.terminal().getWidth();
    }

    public Integer getTerminalHeight() throws IOException {
        return org.jline.terminal.TerminalBuilder.terminal().getHeight();
    }


}