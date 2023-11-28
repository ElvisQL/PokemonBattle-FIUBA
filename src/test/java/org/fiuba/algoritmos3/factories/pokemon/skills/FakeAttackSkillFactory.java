package org.fiuba.algoritmos3.factories.pokemon.skills;

import com.github.underscore.U;
import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.model.pokemon.skills.AttackSkill;

import java.util.List;


public class FakeAttackSkillFactory implements FakeModelFactory<AttackSkill> {
    @Override
    public AttackSkill create(Integer id) {
        return null;
    }

    @Override
    public List<AttackSkill> createList(Integer size) {
        return FakeModelFactory.super.createList(size);
    }

    @Override
    public List<AttackSkill> createList(Integer size, Integer startingId) {
        return FakeModelFactory.super.createList(size, startingId);
    }

}
