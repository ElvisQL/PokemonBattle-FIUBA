package org.fiuba.algoritmos3.factories.pokemon.skills;

import com.github.underscore.U;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.models.pokemon.skills.ConcreteSkill;

import java.util.List;

public class FakeSkillFactory implements FakeModelFactory<ConcreteSkill> {

    @Override
    public ConcreteSkill create(Integer _id) {
        return U.sample(List.of(
                new FakeAttackSkillFactory(),
                new FakeBuffSkillFactory(),
                new FakeStatusSkillFactory()
        )).create(null);
    }

}
