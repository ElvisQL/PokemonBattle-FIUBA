package org.fiuba.algoritmos3.factories.item;

import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.models.item.ReviveItem;

public class FakeReviveItemFactory implements FakeModelFactory<ReviveItem> {
    Faker faker = new Faker();

    @Override
    public ReviveItem create(Integer id) {
        return new ReviveItem(
                id,
                faker.lorem().word(),
                faker.lorem().paragraph(),
                faker.number().numberBetween(1, 100)
        );
    }

}
