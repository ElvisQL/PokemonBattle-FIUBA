package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.model.move.errors.NoRemainingUsesError;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.skills.*;
import org.fiuba.algoritmos3.model.pokemon.status.ParalyzedStatus;
import org.fiuba.algoritmos3.model.pokemon.status.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SkillsTest {

    //AttackSKill
    @Test
    void testApplySkillNoRemainingUses() {
        Pokemon attacker = mock(Pokemon.class);
        Pokemon target = mock(Pokemon.class);

        AttackSkill attackSkill = new AttackSkill("Quick Attack", 20, 0, "A quick attack");

        assertThrows(NoRemainingUsesError.class, () -> attackSkill.apply(attacker, target));
    }


    //BuffSkill
    @Test
    void testApplyBuffToAttack() {
        Pokemon charizard = mock(Pokemon.class);

        int initialAttackPoints = 72;
        when(charizard.getAttackPoints()).thenReturn(initialAttackPoints);

        int expectedAttackIncrease = 10;

        BuffSkill buffSkill = new BuffSkill("Attack Boost", StatType.ATTACK, expectedAttackIncrease, "Boosts attack");

        buffSkill.apply(charizard, null);

        verify(charizard, times(1)).setAttackPoints(initialAttackPoints + expectedAttackIncrease);
        assertTrue(charizard.getAttackPoints() >= initialAttackPoints);
    }

    @Test
    void testApplyBuffToHealth() {
        Pokemon charizard = mock(Pokemon.class);

        int initialAttackPoints = 5;
        when(charizard.getHealth()).thenReturn(initialAttackPoints);

        int expectedAttackIncrease = 10;

        BuffSkill buffSkill = new BuffSkill("Health Boost", StatType.HEALTH, expectedAttackIncrease, "Boosts attack");

        buffSkill.apply(charizard, null);

        verify(charizard, times(1)).setHealth(initialAttackPoints + expectedAttackIncrease);
        assertTrue(charizard.getHealth() >= initialAttackPoints);
    }

    @Test
    void testApplyBuffToSpeed() {
        Pokemon charizard = mock(Pokemon.class);

        int initialAttackPoints = 30;
        when(charizard.getAttackSpeed()).thenReturn(initialAttackPoints);

        int expectedAttackIncrease = 5;

        BuffSkill buffSkill = new BuffSkill("Speed Boost", StatType.SPEED, expectedAttackIncrease, "Boosts Speed");

        buffSkill.apply(charizard, null);

        verify(charizard, times(1)).setAttackSpeed(initialAttackPoints + expectedAttackIncrease);
        assertTrue(charizard.getAttackSpeed() >= initialAttackPoints);
    }

    @Test
    void testApplyBuffToDefense() {
        Pokemon charizard = mock(Pokemon.class);

        int initialAttackSpeed = 10;
        when(charizard.getDefencePoints()).thenReturn(initialAttackSpeed);

        int expectedAttackIncrease = 10;

        BuffSkill buffSkill = new BuffSkill("Defense Boost", StatType.DEFENSE, expectedAttackIncrease, "Boosts Defense");

        buffSkill.apply(charizard, null);

        verify(charizard, times(1)).setDefencePoints(initialAttackSpeed + expectedAttackIncrease);
        assertTrue(charizard.getDefencePoints() >= initialAttackSpeed);
    }

    //SkillTest

    @Test
    void testApplyStatusSkill() {
        Pokemon pokemon = mock(Pokemon.class);
        Pokemon otherPokemon = mock(Pokemon.class);
        Status expectedStatus = mock(ParalyzedStatus.class);

        StatusSkill statusSkill = new StatusSkill("Paralyze", expectedStatus, "Paralyzes the opponent");

        statusSkill.apply(pokemon, otherPokemon);

        verify(otherPokemon, times(1)).addStatus(expectedStatus);
    }

}
