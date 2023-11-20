package org.fiuba.algoritmos3.factories.item;

import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.game.model.item.IncreaseAttackItem;

public class FakeIncreaseAttackItemFactory implements FakeModelFactory<IncreaseAttackItem> {
    Faker faker = new Faker();

    @Override
    public IncreaseAttackItem create(Integer id) {
        return new IncreaseAttackItem(
                id,
                faker.lorem().word(),
                faker.lorem().paragraph(),
                faker.number().numberBetween(1, 100)
        );
    }

}
