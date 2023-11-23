package org.fiuba.algoritmos3.model.move.errors;

import org.fiuba.algoritmos3.model.error.BaseError;

public class SkillError extends BaseError {
    /*
    @param expected invalid object type as String
     */
    public SkillError(String msg) {
        super(msg);
    }
}