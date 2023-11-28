package org.fiuba.algoritmos3.controller;

public enum BattleMessages {

    UNMATCHED("Alrighty then! Are you completely certain though? I'll show you unmatched power!"),
    HUNGRY("You're still here? Ugh, fine. But if I win you're buying me lunch!"),
    PSHH("Pssh... Nothing personal, but i'm gonna crush your dreams."),
    SURE("Are you sure? You can leave, train, and return once you think you're ready, if you want to."),
    SHORT("Be careful what you wish for."),
    HOURS("Finally, I've been standing here looking at nothing for HOURS!");

    private final String label;

    BattleMessages(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
