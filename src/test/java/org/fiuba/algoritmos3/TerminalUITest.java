package org.fiuba.algoritmos3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TerminalUITest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Simple text message")
    void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
        UserInterface ui = new TerminalUI();
        ui.showMessage("Hello this is gpt dependientes speaking, what can i do for you today?");
    }
}
