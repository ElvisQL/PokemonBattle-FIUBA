package org.fiuba.algoritmos3.factories.pokemon.skills;

import com.github.underscore.U;
import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.models.pokemon.skills.StatusSkill;
import org.fiuba.algoritmos3.models.pokemon.status.AsleepStatus;
import org.fiuba.algoritmos3.models.pokemon.status.ConfusedStatus;
import org.fiuba.algoritmos3.models.pokemon.status.ParalyzedStatus;
import org.fiuba.algoritmos3.models.pokemon.status.PoisonedStatus;

import java.util.List;

public class FakeStatusSkillFactory implements FakeModelFactory<StatusSkill> {
    Faker faker = new Faker();

    @Override
    public StatusSkill create(Integer _id) {
        return new StatusSkill(faker.lorem().word(), U.sample(List.of(
                new AsleepStatus(),
                new ConfusedStatus(),
                new ParalyzedStatus(),
                new PoisonedStatus()
        )));
    }

}
