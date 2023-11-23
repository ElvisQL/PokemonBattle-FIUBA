package org.fiuba.algoritmos3.model.menu;

import org.fiuba.algoritmos3.UserInterface;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.menu.operation.Operation;
import org.fiuba.algoritmos3.model.move.GameMoveResult;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class MenuItemTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    class runOperation {
        @Mock
        UserInterface ui;
        @Mock
        Operation<String, String> operation;
        GameMoveResult<String> submenuResult;
        @Mock
        Menu<String> submenu;

        @Test
        void runOperation_successful_result() throws IOException, InvalidSelectionException, BaseError {
            MenuItem<String, String> item = new MenuItem<>("label", operation);

            doReturn(submenu).when(operation).generateSubmenu();
            submenuResult = new GameMoveResult<String>().Ok("it's all good man");
            doReturn(submenuResult).when(submenu).show(ui);

            GameMoveResult<String> menuItemResult = new GameMoveResult<String>().Ok("chillin'");
            doReturn(menuItemResult).when(operation).run(ui, submenuResult);

            GameMoveResult<?> result = item.runOperation(ui);
            assertTrue(result.isOk());
            assertEquals(result, menuItemResult);
        }

        @Test
        void runOperation_error_result() throws IOException, InvalidSelectionException, BaseError {
            MenuItem<String, String> item = new MenuItem<>("label", operation);

            BaseError error = new BaseError("passed");
            doThrow(error).when(operation).generateSubmenu();

            GameMoveResult<?> result = item.runOperation(ui);
            assertTrue(result.isErr());
            assertEquals(result.getError(), error);
        }
    }

}
