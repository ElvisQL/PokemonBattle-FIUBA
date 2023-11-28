package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.factories.item.FakeFixedHealingItemFactory;
import org.fiuba.algoritmos3.model.error.BaseError;
import org.fiuba.algoritmos3.model.error.InvalidSelectionException;
import org.fiuba.algoritmos3.model.item.*;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemTest {

    Pokemon charizard = mock(Pokemon.class);

    @Test
    public void testUse() throws InvalidSelectionException {
        // Arrange
        Pokemon pokemon = mock(Pokemon.class);
        when(pokemon.isDead()).thenReturn(false);
        when(pokemon.getHealth()).thenReturn(50);
        when(pokemon.getMaxHealth()).thenReturn(100);

        FixedHealingItem healingItem = new FixedHealingItem(1, "Potion", "Restores health", 30);

        // Act
        healingItem.use(pokemon);

        // Assert
        verify(pokemon).setHealth(80);
    }

    @Test
    public void testUseWhenPokemonIsDeadShouldThrowException() {
        // Arrange
        Pokemon deadPokemon = mock(Pokemon.class);
        when(deadPokemon.isDead()).thenReturn(true);

        FixedHealingItem healingItem = new FixedHealingItem(1, "Potion", "Restores health", 30);

        // Act & Assert
        assertThrows(InvalidSelectionException.class, () -> healingItem.use(deadPokemon));

        // Verify
        verify(deadPokemon, never()).getHealth();
        verify(deadPokemon, never()).getMaxHealth();
        verify(deadPokemon, never()).setHealth(anyInt());
    }

    @Test
    @DisplayName("Pokemon with max health doesn't get healed")
    public void testHealingItemMaxHealth() throws InvalidSelectionException {
        Pokemon charizard = Mockito.mock(Pokemon.class);
        Integer healthBefore = charizard.getHealth();
        HealingItem heal = new FakeFixedHealingItemFactory().create(1);
        heal.use(charizard);
        Integer healthAfter = charizard.getHealth();
        Assertions.assertEquals(healthBefore, healthAfter);
    }

    @Test
    @DisplayName("Healing Item correctly used heals pokemon")
    public void testHealingItemCorrectlyUsed() throws BaseError {
        ConcreteSkill attack = Mockito.mock(AttackSkill.class);
        Pokemon charizard = Mockito.mock(Pokemon.class);
        Pokemon squirtle = Mockito.mock(Pokemon.class);
        HealingItem heal = new FakeFixedHealingItemFactory().create(1);

        attack.apply(charizard, squirtle);
        Integer healthBefore = charizard.getHealth();
        heal.use(charizard);
        Integer healthAfter = charizard.getHealth();

        assertTrue(healthBefore <= healthAfter);
        assertTrue(healthAfter <= charizard.getMaxHealth());
    }

    @Test
    @DisplayName("Can't heal dead pokemon")
    public void testHealingDead() throws InvalidSelectionException {
        Pokemon charizard = Mockito.mock(Pokemon.class);
        Mockito.when(charizard.isDead()).thenReturn(true);
        HealingItem heal = new FakeFixedHealingItemFactory().create(1);

        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            charizard.kill();
            heal.use(charizard);
        });

        String expectedMessage = "The user chose an invalid Pokemon";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    // TEST INCREASE ATTACK ITEM =======================================================================================
    @Test
    @DisplayName("Can't increase attack when pokemon is dead")
    public void testIncreaseAttackDead() throws InvalidSelectionException {
        Pokemon charizard = Mockito.mock(Pokemon.class);
        Mockito.when(charizard.isDead()).thenReturn(true);

        IncreaseAttackItem incAttack = Mockito.mock(IncreaseAttackItem.class);

        Mockito.when(incAttack.getId()).thenReturn(0);
        Mockito.when(incAttack.getName()).thenReturn("testAttack");
        Mockito.when(incAttack.getDescription()).thenReturn("Description");
        Mockito.doThrow(new InvalidSelectionException("The user chose an invalid Pokemon"))
                .when(incAttack).use(charizard);

        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            incAttack.use(charizard);
        });

        String expectedMessage = "The user chose an invalid Pokemon";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Mockito.verify(incAttack, Mockito.times(1)).use(charizard);
    }

    @Test
    @DisplayName("Increase attack in regular situations")
    public void testIncreasedAttack() throws InvalidSelectionException {
        IncreaseAttackItem incAttack = mock(IncreaseAttackItem.class);
        when(charizard.getAttackPoints()).thenReturn(15);

        when(incAttack.getId()).thenReturn(0);
        when(incAttack.getName()).thenReturn("testAttack");
        when(incAttack.getPercentageIncrease()).thenReturn(5);
        when(incAttack.getDescription()).thenReturn("mi super descripcion");

        incAttack.use(charizard);

        verify(incAttack, times(1)).use(charizard);
        assertTrue(charizard.getAttackPoints() >= 10);
    }


    // TEST INCREASE DEFENSE ===========================================================================================

    @Test
    @DisplayName("Increase defense in regular situations")
    public void testIncreaseDefense() throws InvalidSelectionException {
        IncreaseDefenseItem incDef = mock(IncreaseDefenseItem.class);

        when(incDef.getId()).thenReturn(1);
        when(incDef.getName()).thenReturn("testDefense");
        when(incDef.getDescription()).thenReturn("mi super descripcion");
        when(incDef.getPercentageIncrease()).thenReturn(5);
        when(charizard.getDefencePoints()).thenReturn(10);

        incDef.use(charizard);

        verify(incDef, times(1)).use(charizard);
        assertTrue(charizard.getDefencePoints() > 5);
    }

    @Test
    @DisplayName("Can't increase defense when pokemon is dead")
    public void testIncreaseDefenseDead() throws InvalidSelectionException {
        IncreaseDefenseItem incDef = mock(IncreaseDefenseItem.class);
        Mockito.when(charizard.isDead()).thenReturn(true);

        when(incDef.getId()).thenReturn(1);
        when(incDef.getName()).thenReturn("testDefense");
        when(incDef.getDescription()).thenReturn("mi super descripcion");
        when(incDef.getPercentageIncrease()).thenReturn(15);

        Mockito.doThrow(new InvalidSelectionException("The user chose an invalid Pokemon")).when(incDef).use(charizard);

        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            incDef.use(charizard);
        });

        String expectedMessage = "The user chose an invalid Pokemon";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // TEST REVIVE =====================================================================================================

    @Test
    @DisplayName("If Pokemon isn't weakened, doesn't change status")
    public void testRevivePokemonAlreadyAlive() throws InvalidSelectionException {
        ReviveItem reviveItemMock = mock(ReviveItem.class);


        when(reviveItemMock.getId()).thenReturn(2);
        when(reviveItemMock.getName()).thenReturn("testRevival");
        when(reviveItemMock.getDescription()).thenReturn("mi super descripcion");
        when(reviveItemMock.getRestoredHealth()).thenReturn(100);

        Mockito.doThrow(new InvalidSelectionException("The user chose an invalid The pokemon has to be weakened")).when(reviveItemMock).use(charizard);

        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            reviveItemMock.use(charizard);
        });

        String expectedMessage = "The user chose an invalid The pokemon has to be weakened";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Revive to full health test")
    public void testReviveToFullHealth() throws InvalidSelectionException {
        ReviveItem reviveItemMock = mock(ReviveItem.class);

        when(reviveItemMock.getId()).thenReturn(2);
        when(reviveItemMock.getName()).thenReturn("testRevival");
        when(reviveItemMock.getDescription()).thenReturn("mi super descripcion");
        when(reviveItemMock.getRestoredHealth()).thenReturn(100);

        charizard.kill();
        reviveItemMock.use(charizard);
        Assertions.assertEquals(charizard.getMaxHealth(), charizard.getHealth());
    }

    // TEST RESTORE STATUS =============================================================================================

    @Test
    @DisplayName("Restore Status to NORMAL")
    public void testRestoreToNormal() throws InvalidSelectionException {
        RestoreStatusItem restore = mock(RestoreStatusItem.class);

        when(restore.getId()).thenReturn(3);
        when(restore.getName()).thenReturn("Magic herb");
        when(restore.getDescription()).thenReturn("mi super descripcion");
        restore.use(charizard);
        Assertions.assertTrue(charizard.getStatuses().isEmpty());
    }

    @Test
    @DisplayName("Can't restore status when pokemon is dead")
    public void testCantRestore() throws InvalidSelectionException {
        RestoreStatusItem restore = mock(RestoreStatusItem.class);
        Mockito.when(charizard.isDead()).thenReturn(true);

        when(restore.getId()).thenReturn(3);
        when(restore.getName()).thenReturn("Magic herb");
        when(restore.getDescription()).thenReturn("mi super descripcion");
        Mockito.doThrow(new InvalidSelectionException("The user chose an invalid The pokemon has to be weakened")).when(restore).use(charizard);

        Exception exception = assertThrows(InvalidSelectionException.class, () -> {
            restore.use(charizard);
        });

        String expectedMessage = "The user chose an invalid The pokemon has to be weakened";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

}
