package org.fiuba.algoritmos3.factories.item;

import com.github.underscore.U;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.model.item.Item;

import java.util.List;

public class FakeItemFactory implements FakeModelFactory<Item> {

    @Override
    public Item create(Integer id) {
        return U.sample(List.of(
                new FakeFixedHealingItemFactory(),
                new FakePercentageHealingItemFactory(),
                new FakeIncreaseAttackItemFactory(),
                new FakeIncreaseDefenseItemFactory(),
                new FakeLevelUpItemFactory(),
                new FakeRestoreStatusItemFactory(),
                new FakeReviveItemFactory()
        )).create(id);
    }
}
