package org.fiuba.algoritmos3;


import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface UserInterface {


    void clearScreen();


    /**
     * showMessage
     *
     * @param msg Text to be displayed
     */
    void showMessage(String msg);

    /**
     * askForConfirmation
     *
     * @param msg Text to be displayed
     * @return boolean representing user confirmation
     * @throws IOException In case of error
     */
    Boolean askForConfirmation(String msg) throws IOException;

    /**
     * askForText
     *
     * @param msg Text to be displayed
     * @return String inputted by the user
     * @throws IOException In case of error
     */
    String askForText(String msg) throws IOException;


    /**
     * chooseOption
     *
     * @param promptText Text to be displayed
     * @param items      List of displayable options
     * @return ID of the selected option
     * @throws IOException In case something gets f*cked up
     */


    <T extends UIDisplayable> T chooseOption(String promptText, Collection<T> items) throws IOException;

    <T> T chooseOption(String promptText, Collection<T> items, List<String> itemTexts) throws IOException;


    Integer getTerminalWidth() throws IOException;

    Integer getTerminalHeight() throws IOException;

}