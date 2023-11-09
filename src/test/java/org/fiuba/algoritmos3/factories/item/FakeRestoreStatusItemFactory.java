package org.fiuba.algoritmos3.factories.item;

import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.models.item.RestoreStatusItem;

public class FakeRestoreStatusItemFactory implements FakeModelFactory<RestoreStatusItem> {
    Faker faker = new Faker();

    @Override
    public RestoreStatusItem create(Integer id) {
        return new RestoreStatusItem(
                id,
                faker.lorem().word(),
                faker.lorem().paragraph()
        );
    }

}
