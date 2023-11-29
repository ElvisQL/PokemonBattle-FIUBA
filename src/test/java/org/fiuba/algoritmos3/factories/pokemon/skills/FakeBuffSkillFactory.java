package org.fiuba.algoritmos3.factories.pokemon.skills;

import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.model.pokemon.skills.BuffSkill;

public class FakeBuffSkillFactory implements FakeModelFactory<BuffSkill> {
    Faker faker = new Faker();

    @Override
    public BuffSkill create(Integer _id) {
        return null;
    }

}
