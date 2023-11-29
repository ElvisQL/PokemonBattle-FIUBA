package org.fiuba.algoritmos3;

import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;
import org.fiuba.algoritmos3.model.pokemon.status.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PokemonTest {
    @Mock
    private PokemonSpecies species;

    Pokemon pikachu = new PokemonBuilder()
            .setID(0)
            .setSpecies(
                    new PokemonSpecies(
                            "Pikachu",
                            "Pikachu es un Pokémon eléctrico.",
                            PokemonType.valueOf("Electric"),
                            skills))
            .setSkills(
                    new ArrayList<>(List.of(
                            new AttackSkill("Impactrueno", 80, 1, "hola"),
                            new AttackSkill("Rayo", 70, 1, "hola")
                    ))
            )
            .setRandomAttributes()
            .build();

    @Test
    void testSetHealthMaxHealthLimit() {

        Pokemon pikachu = mock(Pokemon.class);
        when(pikachu.getMaxHealth()).thenReturn(100);
        when(pikachu.getHealth()).thenReturn(100);

        int maxHealth = pikachu.getMaxHealth();

        pikachu.setHealth(150);


        assertEquals(maxHealth, pikachu.getHealth());
    }

    @Test
    void testSetHealth_DeadPokemon() {

        pikachu.setHealth(0);

        assertTrue(pikachu.isDead());
    }

    @Test
    void testAddStatus() {

        Status mockStatus = Mockito.mock(Status.class);

        boolean added = pikachu.addStatus(mockStatus);

        assertTrue(added);
        assertTrue(pikachu.getStatuses().contains(mockStatus));
    }

    @Test
    void testAddStatusDuplicate() {

        Status mockStatus = Mockito.mock(Status.class);

        pikachu.addStatus(mockStatus);
        boolean addedAgain = pikachu.addStatus(mockStatus);

        assertFalse(addedAgain);
        assertEquals(1, pikachu.getStatuses().size());
    }

    @Test
    void testClearStatuses() {

        Pokemon pikachu = mock(Pokemon.class);
        Status mockStatus = Mockito.mock(Status.class);
        pikachu.addStatus(mockStatus);


        pikachu.clearStatuses();


        assertTrue(pikachu.getStatuses().isEmpty());
    }

    @Test
    void testKill() {
        pikachu.kill();
        assertTrue(pikachu.isDead());
    }

    @Test
    void testLevelUp() {
        int initialLevel = pikachu.getLevel();

        pikachu.levelUp(5);

        assertEquals(initialLevel + 5, pikachu.getLevel());
    }

    @Test
    void testGetAttackPoints() {

        pikachu.setAttackPoints(70);

        int result = pikachu.getAttackPoints();

        assertEquals(70, result);
    }

    @Test
    void testGetAttackSpeed() {

        pikachu.setAttackSpeed(3);

        int result = pikachu.getAttackSpeed();

        assertEquals(3, result);
    }

    @Test
    void testGetDefencePoints() {
        pikachu.setDefencePoints(50);

        int result = pikachu.getDefencePoints();

        assertEquals(50, result);
    }

}

