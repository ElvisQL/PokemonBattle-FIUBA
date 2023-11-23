package org.fiuba.algoritmos3.model.move.errors;

public class NoRemainingUsesError extends SkillError {
    /*
    @param expected invalid object type as String
     */
    public NoRemainingUsesError() {
        super("No quedan más usos");
    }
}