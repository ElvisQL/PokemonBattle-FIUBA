package org.fiuba.algoritmos3.factories.pokemon.skills;

import com.github.underscore.U;
import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.models.pokemon.skills.AttackSkill;

public class FakeAttackSkillFactory implements FakeModelFactory<AttackSkill> {
    Faker faker = new Faker();

    @Override
    public AttackSkill create(Integer _id) {
        return new AttackSkill(faker.lorem().word(), faker.number().numberBetween(1, 100), U.random(10));
    }

}
