package org.fiuba.algoritmos3;

import com.github.underscore.U;
import org.fiuba.algoritmos3.factories.pokemon.FakePokemonFactory;
import org.fiuba.algoritmos3.model.GameState;
import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.item.Item;
import org.fiuba.algoritmos3.model.item.RestoreStatusItem;
import org.fiuba.algoritmos3.model.item.ReviveItem;
import org.fiuba.algoritmos3.model.menu.operation.OperationResult;
import org.fiuba.algoritmos3.model.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.menu.operation.errors.OwnershipError;
import org.fiuba.algoritmos3.model.move.ChangePokemon;
import org.fiuba.algoritmos3.model.move.Surrender;
import org.fiuba.algoritmos3.model.move.UseItem;
import org.fiuba.algoritmos3.model.move.UseSkill;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.*;
import org.fiuba.algoritmos3.model.pokemon.status.ParalyzedStatus;
import org.javatuples.Pair;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A GameMove")
public class MoveTest {

    // SETUP ===========================================================================================================
    Pokemon charizard = new PokemonBuilder()
            .setID(0)
            .setRandomAttributes()
            .setSpecies(new PokemonSpecies(
                    "Charizard",
                    "Charizard es un Pokémon de tipo Fuego/Volador. Es la evolución final de Charmander y es conocido por su poderoso aliento de fuego y su apariencia similar a un dragón.",
                    PokemonType.valueOf("Fire")
            ))
            .setSkills(
                    new ArrayList<>(List.of(
                            new AttackSkill("Lanzallamas", 90, U.random(10)),
                            new AttackSkill("Vuelo", 70, U.random(10))
                    )))
            .build();

    Pokemon squirtle = new PokemonBuilder()
            .setID(1)
            .setRandomAttributes()
            .setSpecies(
                    new PokemonSpecies(
                            "Squirtle",
                            "Squirtle es un Pokémon de tipo Agua. Es uno de los Pokémon iniciales originales y es conocido por sus cañones de agua en su espalda.",
                            PokemonType.valueOf("Water")
                    ))
            .setSkills(
                    new ArrayList<>(List.of(
                            new AttackSkill("Pistola Agua", 40, U.random(10)),
                            new BuffSkill("Refugio", StatType.valueOf("DEFENSE"), 20)
                    )))
            .build();
    Pokemon jigglypuff = new FakePokemonFactory().create(2);
    Pokemon pikachu = new PokemonBuilder()
            .setID(3)
            .setRandomAttributes()
            .setSpecies(
                    new PokemonSpecies(
                            "Pikachu",
                            "Pikachu es un Pokémon de tipo Eléctrico conocido por su cola en forma de rayo. Es uno de los Pokémon más icónicos y queridos, a menudo reconocido como la mascota de la franquicia Pokémon.",
                            PokemonType.valueOf("Electric")
                    ))
            .setSkills(
                    new ArrayList<>(List.of(
                            new AttackSkill("Rayo", 90, 0),
                            new StatusSkill("Onda Trueno", new ParalyzedStatus())
                    )))
            .build();

    Player john = new Player(
            "John",
            List.of(charizard, squirtle),
            new ArrayList<>()
    );

    Player jane = new Player(
            "Jane",
            List.of(pikachu, jigglypuff),
            new ArrayList<>()
    );

    UserInterface ui = new MockUI();
    GameState gameState = new GameState(john, jane);
    ChangePokemon change = new ChangePokemon(gameState);
    Surrender surrender = new Surrender(gameState);
    UseItem useItem = new UseItem(gameState);
    UseSkill useSkill = new UseSkill(gameState);

    protected void checkAssertionMessage(Exception exception, String object) {
        String expectedMessage = "The user chose an invalid " + object;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Nested
    @DisplayName("with John as the current player")
    class JohnAsThePlayer {
        @BeforeEach
        public void setJohnAsThePlayer() {
            gameState.setCurrentPlayer(john);
        }

        @Nested
        @DisplayName("when the move is ChangePokemon")
        class ChangePokemon {
            @Test
            @DisplayName("it changes the currentPokemon")
            public void testChangeCurrentPokemon() throws InvalidSelectionException {
                john.setCurrentPokemon(charizard);
                Assertions.assertEquals(charizard, john.getCurrentPokemon());

                change.run(ui, new OperationResult<Pokemon>().Ok(squirtle));

                Assertions.assertEquals(john.getCurrentPokemon(), squirtle);
            }

            @Test
            @DisplayName("changing Pokemon to one that isn't in that player's roster fails")
            public void testChangePokemon_PokemonNotInRoster() {
                Exception exception = assertThrows(InvalidSelectionException.class, () -> {
                    change.run(ui, new OperationResult<Pokemon>().Ok(pikachu));
                });

                checkAssertionMessage(exception, "Pokemon");
            }

            @Test
            @DisplayName("changing Pokemon to a weakened one fails")
            public void testChangePokemon_PokemonDead() throws InvalidSelectionException {
                john.setCurrentPokemon(charizard);
                squirtle.kill();
                Exception exception = assertThrows(InvalidSelectionException.class, () -> {
                    change.run(ui, new OperationResult<Pokemon>().Ok(squirtle));
                });

                checkAssertionMessage(exception, "Pokemon");
            }
        }

        @Nested
        @DisplayName("when the move is UseItem")
        class UseItem {
            ReviveItem reviveFullItem = new ReviveItem(1, "testRevival", "mi super descripcion", 100);
            RestoreStatusItem restore = new RestoreStatusItem(2, "Magic herb", "mi super descripcion");

            @Test
            @DisplayName("using a restore item affects the expected Pokemon")
            public void testUseItemOnCurrentPokemon() throws InvalidSelectionException {
                john.getItems().clear();
                john.getItems().add(restore);

                john.setCurrentPokemon(squirtle);
                squirtle.addStatus(new ParalyzedStatus());

                OperationResult<Pair<Item, Pokemon>> submenuResult = new OperationResult<Pair<Item, Pokemon>>().Ok(new Pair<>(
                        restore,
                        squirtle
                ));
                OperationResult<?> result = useItem.run(ui, submenuResult);

                assertTrue(result.isOk());

                assertTrue(squirtle.getStatuses().isEmpty());
            }

            @Test
            @DisplayName("using a revive item on a Pokemon that isn't the current one works")
            public void testUseItemAnyPokemon() throws InvalidSelectionException {
                john.setCurrentPokemon(charizard);
                john.getItems().clear();
                john.getItems().add(reviveFullItem);

                OperationResult<Pair<Item, Pokemon>> submenuResult = new OperationResult<Pair<Item, Pokemon>>().Ok(new Pair<>(
                        reviveFullItem,
                        squirtle
                ));

                squirtle.kill();
                OperationResult<?> result = useItem.run(ui, submenuResult);
                assertTrue(result.isOk());
                assertFalse(squirtle.isDead());
            }

            @Test
            @DisplayName("using an item outside the current player's inventory fails")
            public void testUseItemNotOwnedByPlayer() throws InvalidSelectionException {
                john.setCurrentPokemon(squirtle);
                john.getItems().clear();

                OperationResult<Pair<Item, Pokemon>> submenuResult = new OperationResult<Pair<Item, Pokemon>>().Ok(new Pair<>(
                        restore,
                        squirtle
                ));

                OperationResult<?> result = useItem.run(ui, submenuResult);
                assertTrue(result.isErr());

                assertEquals(result.getError().getClass(), OwnershipError.class);
            }
        }

        @Nested
        @DisplayName("when the move is UseSkill")
        class UseSkill {
            @Test
            @DisplayName("Use Skill afecta correctamente a Pokemon enemigo")
            public void testUseSkill() throws NoRemainingUsesError, IOException, InvalidSelectionException {
                john.setCurrentPokemon(squirtle);
                Integer quirtleDefense = squirtle.getDefencePoints();
                useSkill.run(ui, new OperationResult<ConcreteSkill>().Ok(squirtle.getSkills().get(1)));
                Assertions.assertTrue(squirtle.getDefencePoints() > quirtleDefense);
            }

            @Test
            @DisplayName("Usar skill sin usos disponibles falla")
            public void testUseUnavailableSkill() throws InvalidSelectionException {
                john.setCurrentPokemon(squirtle);
                ConcreteSkill skillWithoutUses = new AttackSkill("Super Pistola Agua", 40, 0);
                squirtle.getSkills().add(skillWithoutUses);

                Exception exception = assertThrows(NoRemainingUsesError.class, () -> {
                    useSkill.run(ui, new OperationResult<ConcreteSkill>().Ok(skillWithoutUses));
                });
            }
        }
    }


}
