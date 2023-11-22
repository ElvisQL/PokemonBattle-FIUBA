package org.fiuba.algoritmos3.model.menu;

import org.fiuba.algoritmos3.MockUI;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuTest {

    @Spy
    MockUI spiedUI = new MockUI();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void show() throws IOException, InvalidSelectionException {
        MenuItem<String, String> item1 = new MenuItem<>("label1", "content1");
        MenuItem<String, String> item2 = spy(new MenuItem<>("label2", "content2"));
        MenuItem<String, String> item3 = new MenuItem<>("label3", "content3");

        List<MenuItem<String, ?>> items = List.of(
                item1, item2, item3
        );

        Menu<String> menu = new Menu<>(items);

        doReturn(item2).when(spiedUI).chooseOption("choose an option: ", items);
        menu.show(spiedUI);
        verify(spiedUI).chooseOption("choose an option: ", items);

        verify(item2).runOperation(spiedUI);
    }

    @Test
    void isEmpty() {
    }
}