package org.fiuba.algoritmos3.factories.pokemon;

import com.github.underscore.U;
import net.datafaker.Faker;
import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;

import java.util.List;

public class FakePokemonSpeciesFactory implements FakeModelFactory<PokemonSpecies> {
    Faker faker = new Faker();

    @Override
    public PokemonSpecies create(Integer _id) {

        List<ConcreteSkill> skills = null;
        return new PokemonSpecies(
                faker.pokemon().name(),
                faker.lorem().paragraph(),
                U.sample(List.of(PokemonType.values())),
                skills);
    }
}
