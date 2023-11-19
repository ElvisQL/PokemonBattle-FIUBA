package org.fiuba.algoritmos3.factories.item;

import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.game.model.item.PercentageHealingItem;

public class FakePercentageHealingItemFactory implements FakeModelFactory<PercentageHealingItem> {
    Faker faker = new Faker();

    @Override
    public PercentageHealingItem create(Integer id) {
        return new PercentageHealingItem(
                id,
                faker.lorem().word(),
                faker.lorem().paragraph(),
                faker.number().numberBetween(1, 100)
        );
    }

}
