package org.fiuba.algoritmos3.factories.pokemon.skills;

import com.github.underscore.U;
import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.game.model.pokemon.skills.BuffSkill;
import org.fiuba.algoritmos3.game.model.pokemon.skills.StatType;

import java.util.List;

public class FakeBuffSkillFactory implements FakeModelFactory<BuffSkill> {
    Faker faker = new Faker();

    @Override
    public BuffSkill create(Integer _id) {
        return new BuffSkill(faker.lorem().word(), U.sample(List.of(StatType.values())), U.random(10));
    }

}
