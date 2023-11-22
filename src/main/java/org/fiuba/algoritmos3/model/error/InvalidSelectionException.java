package org.fiuba.algoritmos3.model.error;

public class InvalidSelectionException extends Exception {
    /*
    @param expected invalid object type as String
     */
    public InvalidSelectionException(String msg) {
        super("The user chose an invalid " + msg);
    }
}