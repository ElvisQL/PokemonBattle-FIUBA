package org.fiuba.algoritmos3.factories.pokemon;

import org.fiuba.algoritmos3.factories.FakeModelFactory;
import org.fiuba.algoritmos3.factories.pokemon.skills.FakeSkillFactory;
import org.fiuba.algoritmos3.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.model.pokemon.PokemonBuilder;

public class FakePokemonFactory implements FakeModelFactory<Pokemon> {

    @Override
    public Pokemon create(Integer id) {
        return new PokemonBuilder()
                .setID(id)
                .setSpecies(new FakePokemonSpeciesFactory().create(null))
                .setSkills(new FakeSkillFactory().createList(2))
                .setRandomAttributes()
                .build();
    }
}
