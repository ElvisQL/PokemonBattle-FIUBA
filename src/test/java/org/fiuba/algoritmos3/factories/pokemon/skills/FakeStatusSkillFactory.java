package org.fiuba.algoritmos3.factories.pokemon.skills;

import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.model.pokemon.skills.StatusSkill;

public class FakeStatusSkillFactory implements FakeModelFactory<StatusSkill> {
    Faker faker = new Faker();

    @Override
    public StatusSkill create(Integer _id) {
        return null;

    }
}
