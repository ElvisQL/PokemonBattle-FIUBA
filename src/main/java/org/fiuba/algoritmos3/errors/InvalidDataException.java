package org.fiuba.algoritmos3.errors;

public class InvalidDataException extends Exception {
    /*
    @param expected invalid file name as String
     */
    public InvalidDataException(String msg) {
        super("Not able to correctly parse all data. File: " + msg);
    }
}
