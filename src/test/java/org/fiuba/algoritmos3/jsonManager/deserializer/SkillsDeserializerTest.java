package org.fiuba.algoritmos3.jsonManager.deserializer;

import org.fiuba.algoritmos3.model.error.InvalidDataException;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SkillsDeserializerTest {

    @Test
    void getSkills() throws InvalidDataException {
        HashMap<Integer, ConcreteSkill> skillsDeserializer = new SkillsDeserializer().getSkills();
        assertNotNull(skillsDeserializer);
        Assertions.assertFalse(skillsDeserializer.isEmpty());
        Assertions.assertEquals(skillsDeserializer.get(75).getName(), "Calm Mind");
    }
}