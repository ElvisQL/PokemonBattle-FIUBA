package org.fiuba.algoritmos3;

/**
 * Displayable
 * Makes the implentee displayable within a UserInterface
 */
public interface UIDisplayable {
    /**
     * itemId
     *
     * @return A unique identifier
     */
    String getItemId();

    /**
     * itemText
     *
     * @return Text to be displayed on screen
     */
    String getItemText();
}