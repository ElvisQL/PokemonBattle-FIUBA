package org.fiuba.algoritmos3;

import com.github.underscore.U;
import org.fiuba.algoritmos3.game.menu.operation.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.game.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.game.model.pokemon.skills.BuffSkill;
import org.fiuba.algoritmos3.game.model.pokemon.skills.StatType;
import org.fiuba.algoritmos3.game.model.pokemon.status.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StatusTest {
    @Test
    @DisplayName("An asleep pokemon can use some skill at the 4th turn")
    public void testAsleepStatusCanUseSkillProbability() throws NoRemainingUsesError, IOException {
        Pokemon charizard = Mockito.mock(Pokemon.class);
        Pokemon Pikachu = Mockito.mock(Pokemon.class);
        AsleepStatus asleepStatus = new AsleepStatus();
        int totalTurns = 4;

        for (int turn = 0; turn < totalTurns; turn++) {
            asleepStatus.canUseSkill(charizard);
        }

        double awakenProbability = (double) asleepStatus.getElapsedRounds() / totalTurns;

        Assertions.assertEquals(1.0, awakenProbability);

    }

    @Test
    @DisplayName("A confused Pokemon should hit itself with 1/3 probability and it shouldn't hit itself with 2/3 probability")
    public void testConfusedPokemonHitsItself() {
        Pokemon snorlax = Mockito.mock(Pokemon.class);
        ConfusedStatus confusedStatus = new ConfusedStatus();
        Mockito.when(snorlax.getHealth()).thenReturn(50);
        Mockito.when(snorlax.getMaxHealth()).thenReturn(100);


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
        double tolerance = 0.05;  // Tolerance for randomness

        Assertions.assertTrue(Math.abs(hitProbability - expectedHitProbability) < tolerance);


        double expectedMissProbability = 2.0 / 3.0;
        Assertions.assertTrue(Math.abs(missProbability - expectedMissProbability) < tolerance);
    }


    @Test
    @DisplayName("A paralyzed pokemon cant use skill with 1/2 probability and can use with 1/2 probabilty")
    public void testParalyzedStatusCantUseSkill() {
        Pokemon Bulbasaur = Mockito.mock(Pokemon.class);
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
    @DisplayName("A poisoned pokemon loses 5% health each round")
    public void testPoisonedStatusReducesHealth() {
        Pokemon pickachu = Mockito.mock(Pokemon.class);
        Mockito.when(pickachu.getMaxHealth()).thenReturn(100);

        PoisonedStatus poisonedStatus = new PoisonedStatus();
        int initialHealth = 80; // Supongo que el Pokémon tiene 80 de salud

        Mockito.when(pickachu.getHealth()).thenReturn(initialHealth);

        poisonedStatus.apply(pickachu);

        int expectedHealth = initialHealth - (int) (0.05 * pickachu.getMaxHealth()); // 5% de la salud máxima
        Mockito.verify(pickachu).setHealth(expectedHealth);
    }


    @Test
    @DisplayName("Pokemon with multiple status")
    public void testMultipleStatus() throws NoRemainingUsesError, IOException {
        Pokemon squirtle = new PokemonBuilder()
                .setID(0)
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
                        ))
                )
                .setRandomAttributes()
                .build();

        Status status1 = new AsleepStatus();
        Status status2 = new ConfusedStatus();
        Status status3 = new PoisonedStatus();
        Status status4 = new ParalyzedStatus();

        squirtle.addStatus(status1);
        squirtle.addStatus(status2);
        squirtle.addStatus(status3);
        squirtle.addStatus(status4);


        Set<Status> statuses = squirtle.getStatuses();
        Assertions.assertTrue(statuses.contains(status1));
        Assertions.assertTrue(statuses.contains(status2));
        Assertions.assertTrue(statuses.contains(status3));
        Assertions.assertTrue(statuses.contains(status4));

    }
}









