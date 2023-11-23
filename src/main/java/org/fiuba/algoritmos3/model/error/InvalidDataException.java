package org.fiuba.algoritmos3.model.error;

public class InvalidDataException extends BaseError {
    /*
    @param expected invalid file name as String
     */
    public InvalidDataException(String msg) {
        super("Not able to correctly parse all data. File: " + msg);
    }
}
