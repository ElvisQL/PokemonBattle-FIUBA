package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.model.Player;
import org.fiuba.algoritmos3.model.PlayerData;
import org.fiuba.algoritmos3.model.item.IncreaseDefenseItem;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.status.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatusTest {
    @Test
    @DisplayName("An asleep pokemon can use some skill at the 4th turn")
    public void testAsleepStatusCanUseSkillProbability() {
        Pokemon charizard = mock(Pokemon.class);
        AsleepStatus asleepStatus = new AsleepStatus();
        int totalTurns = 4;

        for (int turn = 0; turn < totalTurns; turn++) {
            asleepStatus.canUseSkill(charizard);
        }

        double awakenProbability = (double) asleepStatus.getElapsedRounds() / totalTurns;

        assertEquals(1.0, awakenProbability);

    }

    @Test
    @DisplayName("A confused Pokemon should hit itself with 1/3 probability and it shouldn't hit itself with 2/3 probability")
    public void testConfusedPokemonHitsItself() {
        Pokemon snorlax = mock(Pokemon.class);
        ConfusedStatus confusedStatus = new ConfusedStatus();
        when(snorlax.getHealth()).thenReturn(50);
        when(snorlax.getMaxHealth()).thenReturn(100);


        int numHits = 0;
        int numMisses = 0;
        int totalTests = 1000;

        for (int i = 0; i < totalTests; i++) {
            boolean canUseSkill = confusedStatus.canUseSkill(snorlax);
            if (canUseSkill) {
                numMisses++;
            } else {
                numHits++;
            }
        }


        double hitProbability = (double) numHits / totalTests;
        double missProbability = (double) numMisses / totalTests;


        double expectedHitProbability = 1.0 / 3.0;
        double tolerance = 0.05;

        Assertions.assertTrue(Math.abs(hitProbability - expectedHitProbability) < tolerance);


        double expectedMissProbability = 2.0 / 3.0;
        Assertions.assertTrue(Math.abs(missProbability - expectedMissProbability) < tolerance);
    }

    @Test
    @DisplayName("A poisoned pokemon loses 5% health each round")
    public void testPoisonedStatusReducesHealth() {
        Pokemon pickachu = mock(Pokemon.class);
        when(pickachu.getMaxHealth()).thenReturn(100);

        PoisonedStatus poisonedStatus = new PoisonedStatus();
        int initialHealth = 80; // Supongo que el Pokémon tiene 80 de salud

        when(pickachu.getHealth()).thenReturn(initialHealth);

        poisonedStatus.apply(pickachu);

        int expectedHealth = initialHealth - (int) (0.05 * pickachu.getMaxHealth()); // 5% de la salud máxima
        Mockito.verify(pickachu).setHealth(expectedHealth);
    }

    @Test
    @DisplayName("A paralyzed pokemon cant use skill with 1/2 probability and can use with 1/2 probabilty")
    public void testParalyzedStatusCantUseSkill() {
        Pokemon Bulbasaur = mock(Pokemon.class);
        ParalyzedStatus paralyzedStatus = new ParalyzedStatus();

        int totalTests = 1000;
        int blockedSkillCount = 0;
        int allowedSkillCount = 0;

        for (int i = 0; i < totalTests; i++) {
            if (paralyzedStatus.canUseSkill(Bulbasaur)) {
                allowedSkillCount++;
            } else {
                blockedSkillCount++;
            }
        }
        double blockedSkillProbability = (double) blockedSkillCount / totalTests;


        double expectedBlockedSkillProbability = 0.5;
        double tolerance = 0.05; // 5% de margen de error

        Assertions.assertTrue(Math.abs(blockedSkillProbability - expectedBlockedSkillProbability) < tolerance);

        double allowedSkillProbability = (double) allowedSkillCount / totalTests;

        double expectedAllowedSkillProbability = 0.5;
        double toleranceAllowed = 0.05;

        Assertions.assertTrue(Math.abs(allowedSkillProbability - expectedAllowedSkillProbability) < toleranceAllowed);
    }

    @Test
    @DisplayName("Pokemon with multiple status")
    public void testMultipleStatus() {
        Pokemon charizardMock = mock(Pokemon.class);
        when(charizardMock.getID()).thenReturn(1);

        Pokemon squirtleMock = mock(Pokemon.class);
        when(squirtleMock.getID()).thenReturn(2);

        Player activePlayerMock = mock(Player.class);
        when(activePlayerMock.getName()).thenReturn("John");
        when(activePlayerMock.getItems()).thenReturn(
                List.of(new IncreaseDefenseItem(1, "testDefense", "mi super descripcion", 15))
        );
        when(activePlayerMock.getPokemons()).thenReturn(List.of(charizardMock, squirtleMock));


        PlayerData playerData = new PlayerData().buildFromActivePlayer(activePlayerMock);

        assertEquals("John", playerData.getName());
        assertEquals(1, playerData.getItems().size());
        Assertions.assertTrue(playerData.getItems().containsKey(1));
        assertEquals(1, playerData.getItems().get(1));
        assertEquals(2, playerData.getPokemons().size());
        Assertions.assertTrue(playerData.getPokemons().contains(1));
    }

    @Test
    void testGetName() {
        DeadStatus deadStatus = new DeadStatus();

        String result = deadStatus.getName();

        assertEquals("Dead", result);
    }


}