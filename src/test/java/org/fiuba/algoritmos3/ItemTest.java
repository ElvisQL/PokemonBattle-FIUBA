package org.fiuba.algoritmos3;

import com.github.underscore.U;
import org.fiuba.algoritmos3.factories.item.FakeFixedHealingItemFactory;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.item.*;
import org.fiuba.algoritmos3.model.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.BuffSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.StatType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    // SETUP ==========================================================================================================
    Pokemon charizard = new PokemonBuilder()
            .setID(0)
            .setSpecies(
                    new PokemonSpecies(
                            "Charizard",
                            "Charizard es un Pokémon de tipo Fuego/Volador. Es la evolución final de Charmander y es conocido por su poderoso aliento de fuego y su apariencia similar a un dragón.",
                            PokemonType.valueOf("Fire")
                    ))
            .setSkills(
                    new ArrayList<>(List.of(
                            new AttackSkill("Lanzallamas", 90, U.random(10)),
                            new AttackSkill("Vuelo", 70, U.random(10))
                    ))
            )
            .setRandomAttributes()
            .build();

    Pokemon squirtle = new PokemonBuilder()
            .setID(1)
            .setSpecies(
                    new PokemonSpecies(
                            "Squirtle",
                            "Squirtle es un Pokémon de tipo Agua. Es uno de los Pokémon iniciales originales y es conocido por sus cañones de agua en su espalda.",
                            PokemonType.valueOf("Water")
                    ))
            .setSkills(
                    new ArrayList<>(List.of(
                            new AttackSkill("Pistola Agua", 40, U.random(10)),
                            new BuffSkill("Refugio", StatType.valueOf("DEFENSE"), 20) //
                    ))
            )
            .setRandomAttributes()
            .build();


    // TEST HEALING ITEM ===============================================================================================

    // TEST HEALING PERCENTAGE


    // TEST HEALING FIXED ==============================================================================================
    HealingItem heal = new FakeFixedHealingItemFactory().create(1);

    @Test
    @DisplayName("Pokemon with max health doesn't get healed")
    public void testHealingItemMaxHealth() throws InvalidSelectionException {
        Integer healthBefore = charizard.getHealth();
        heal.use(charizard);
        Integer healthAfter = charizard.getHealth();
        Assertions.assertEquals(healthBefore, healthAfter);
    }

    @Test
    @DisplayName("Healing Item correctly used heals pokemon")
    public void testHealingItemCorrectlyUsed() throws InvalidSelectionException, NoRemainingUsesError, IOException {
        ConcreteSkill attack = new AttackSkill("Lanzallamas", 90, U.random(10));
        attack.use(charizard, squirtle);
        Integer healthBefore = charizard.getHealth();
        heal.use(charizard);
        Integer healthAfter = charizard.getHealth();
        assertTrue(healthBefore <= healthAfter);
        assertTrue(healthAfter <= charizard.getMaxHealth());
    }

    @Test
    @DisplayName("Can't heal dead pokemon")
    public void testHealingDead() throws InvalidSelectionException {
        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            charizard.kill();
            heal.use(charizard);
        });

        String expectedMessage = "The user chose an invalid Pokemon";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    // TEST INCREASE ATTACK ITEM =======================================================================================
    IncreaseAttackItem incAttack = new IncreaseAttackItem(0, "testAttack", "mi super descripcion", 5) {
    };

    @Test
    @DisplayName("Can't increase attack when pokemon is dead")
    public void testIncreaseAttackDead() throws InvalidSelectionException {
        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            charizard.kill();
            incAttack.use(charizard);
        });

        String expectedMessage = "The user chose an invalid Pokemon";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Increase attack in regular situations")
    public void testIncreaseAttack() throws InvalidSelectionException {
        Integer attackBefore = charizard.getAttackPoints();
        incAttack.use(charizard);
        Integer attackAfter = charizard.getAttackPoints();
        assertTrue(attackBefore < attackAfter);
    }

    // TEST INCREASE DEFENSE ===========================================================================================

    IncreaseDefenseItem incDef = new IncreaseDefenseItem(1, "testDefense", "mi super descripcion", 15) {
    };

    @Test
    @DisplayName("Increase defense in regular situations")
    public void testIncreaseDefense() throws InvalidSelectionException {
        Integer defBefore = charizard.getDefencePoints();
        incDef.use(charizard);
        Integer defAfter = charizard.getDefencePoints();
        assertTrue(defBefore < defAfter);
    }

    @Test
    @DisplayName("Can't increase defense when pokemon is dead")
    public void testIncreaseDefenseDead() throws InvalidSelectionException {
        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            charizard.kill();
            incDef.use(charizard);
        });

        String expectedMessage = "The user chose an invalid Pokemon";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // TEST REVIVE =====================================================================================================

    ReviveItem full = new ReviveItem(2, "testRevival", "mi super descripcion", 100);

    @Test
    @DisplayName("If Pokemon isn't weakened, doesn't change status")
    public void testRevivePokemonAlreadyAlive() throws InvalidSelectionException {
        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            full.use(charizard);
        });

        String expectedMessage = "The user chose an invalid The pokemon has to be weakened";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Revive to full health test")
    public void testReviveToFullHealth() throws InvalidSelectionException {
        charizard.kill();
        full.use(charizard);
        Assertions.assertEquals(charizard.getMaxHealth(), charizard.getHealth());
    }

    // TEST RESTORE STATUS =============================================================================================
    RestoreStatusItem restore = new RestoreStatusItem(3, "Magic herb", "mi super descripcion");

    @Test
    @DisplayName("Restore Status to NORMAL")
    public void testRestoreToNormal() throws InvalidSelectionException {
        restore.use(charizard);
        Assertions.assertTrue(charizard.getStatuses().isEmpty());
    }

    @Test
    @DisplayName("Can't restore status when pokemon is dead")
    public void testCantRestore() {
        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            charizard.kill();
            restore.use(charizard);
        });

        String expectedMessage = "The user chose an invalid Pokemon";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }


}
