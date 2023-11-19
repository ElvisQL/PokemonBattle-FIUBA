package org.fiuba.algoritmos3.factories.item;

import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.game.model.item.LevelUpItem;

public class FakeLevelUpItemFactory implements FakeModelFactory<LevelUpItem> {
    Faker faker = new Faker();

    @Override
    public LevelUpItem create(Integer id) {
        return new LevelUpItem(
                id,
                faker.lorem().word(),
                faker.lorem().paragraph(),
                faker.number().numberBetween(1, 100)
        );
    }

}
