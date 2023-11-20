package org.fiuba.algoritmos3.factories.item;

import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.game.model.item.IncreaseDefenseItem;

public class FakeIncreaseDefenseItemFactory implements FakeModelFactory<IncreaseDefenseItem> {
    Faker faker = new Faker();

    @Override
    public IncreaseDefenseItem create(Integer id) {
        return new IncreaseDefenseItem(
                id,
                faker.lorem().word(),
                faker.lorem().paragraph(),
                faker.number().numberBetween(1, 100)
        );
    }

}
