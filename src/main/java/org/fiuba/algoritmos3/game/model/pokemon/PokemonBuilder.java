package org.fiuba.algoritmos3.game.model.pokemon;

import com.github.underscore.U;
import org.fiuba.algoritmos3.game.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.game.model.pokemon.status.Status;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static org.fiuba.algoritmos3.Constants.INITIAL_HEALTH;
import static org.fiuba.algoritmos3.Constants.SKILLS_LIMIT;

public class PokemonBuilder {

    private PokemonType type;
    private String history;
    private Integer level;
    private List<ConcreteSkill> skills;
    private final HashSet<Status> statuses = new HashSet<>();
    private Integer attackSpeed;
    private Integer attackPoints;
    private Integer defencePoints;
    private Integer baseHealth;
    private String name;
    private PokemonSpecies species;

    private Integer ID;

    public PokemonBuilder setSpecies(PokemonSpecies species) {
        this.species = species;
        this.type = species.getType();
        this.name = species.getName();
        this.history = species.getHistory();
        return this;
    }

    public PokemonBuilder setRandomAttributes() {
        this.level = U.random(1, 100);
        this.attackPoints = U.random(1, 100);
        this.defencePoints = U.random(1, 100);
        this.attackSpeed = U.random(1, 100);
        this.baseHealth = INITIAL_HEALTH;
        return this;
    }

    public PokemonBuilder setAttackSpeed(Integer attackSpeed) {
        this.attackSpeed = attackSpeed;
        return this;
    }

    public PokemonBuilder setAttackPoints(Integer attackPoints) {
        this.attackPoints = attackPoints;
        return this;
    }

    public PokemonBuilder setDefencePoints(Integer defencePoints) {
        this.defencePoints = defencePoints;
        return this;
    }

    public PokemonBuilder setLevel(Integer lvl) {
        this.level = lvl;
        return this;
    }

    public PokemonBuilder setBaseHealth(Integer base) {
        this.baseHealth = base;
        return this;
    }

    public PokemonBuilder setID(Integer id) {
        this.ID = id;
        return this;
    }

    public PokemonBuilder setSkills(List<ConcreteSkill> skillsList) {
        if (skillsList.size() > SKILLS_LIMIT) {
            this.skills = List.copyOf(U.sample(skillsList, SKILLS_LIMIT));
        } else {
            this.skills = skillsList;
        }
        return this;
    }

    public Pokemon build() {
        if (!validate()) {
            return null;
        }

        Pokemon pokemon = new Pokemon();
        pokemon.ID = this.ID;
        pokemon.name = this.name;
        pokemon.type = this.type;
        pokemon.level = this.level;
        pokemon.history = this.history;
        pokemon.species = this.species;
        pokemon.baseHealth = this.baseHealth;
        pokemon.maxHealth = this.baseHealth * this.level;
        pokemon.health = pokemon.maxHealth;

        pokemon.attackSpeed = this.attackSpeed;
        pokemon.attackPoints = this.attackPoints;
        pokemon.defencePoints = this.defencePoints;

        pokemon.skills = this.skills;
        pokemon.statuses = this.statuses;
        return pokemon;
    }

    private boolean validate() {
        List<Object> requiredFields = List.of(
                type, level, species, baseHealth, statuses, skills, ID
        );
        return U.all(requiredFields, Objects::nonNull);
    }

}
