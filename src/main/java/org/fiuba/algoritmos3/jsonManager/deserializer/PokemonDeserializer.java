package org.fiuba.algoritmos3.jsonManager.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import org.fiuba.algoritmos3.game.error.InvalidDataException;
import org.fiuba.algoritmos3.jsonManager.JsonManager;
import org.fiuba.algoritmos3.jsonManager.JsonPath;
import org.fiuba.algoritmos3.game.model.pokemon.Pokemon;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonBuilder;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.game.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.game.model.pokemon.skills.ConcreteSkill;

import java.util.ArrayList;
import java.util.HashMap;

import static org.fiuba.algoritmos3.Constants.SKILLS_LIMIT;

public class PokemonDeserializer {

    private HashMap<Integer, Pokemon> pokemon;
    private HashMap<Integer, ConcreteSkill> skillsIndex;

    public PokemonDeserializer() throws InvalidDataException {
        try {
            skillsIndex = new SkillsDeserializer().getSkills();

            JsonNode jsonNode = new JsonManager()
                    .Reader(JsonPath.POKEMON.toString())
                    .getNode();
            this.pokemon = this.use(jsonNode);
            // TODO podrian ser singleton?
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }


    }

    public HashMap<Integer, Pokemon> getPokemon() {
        return pokemon;
    }

    private ArrayList<ConcreteSkill> selectSkills(JsonNode jsonNode) throws InvalidDataException {

        if (jsonNode != null && jsonNode.isArray()) {

            int maxSkill = SKILLS_LIMIT;
            ArrayList<ConcreteSkill> skillArrayList = new ArrayList<>(SKILLS_LIMIT);
            for (JsonNode skill : jsonNode) {
                if (maxSkill > 0) {
                    skillArrayList.add(skillsIndex.get(skill.asInt()));
                    maxSkill--;
                }
            }
            return skillArrayList;

        } else {
            throw new InvalidDataException("pokemon.json - INVALID SKILL");
        }
    }

    private HashMap<Integer, Pokemon> use(JsonNode jsonNode) throws InvalidDataException {

        HashMap<Integer, Pokemon> pokeHash = new HashMap<>();
        if (jsonNode != null && jsonNode.isArray()) {

            for (JsonNode poke : jsonNode) {
                // Construimos Pokemon
                Pokemon pokemon = new PokemonBuilder()
                        .setID(poke.get("ID").asInt())
                        .setSpecies(
                                new PokemonSpecies(
                                        poke.get("name").asText(),
                                        poke.get("history").asText(),
                                        PokemonType.valueOf(poke.get("type").asText())
                                ))
                        .setAttackPoints(poke.get("baseAttack").asInt())
                        .setAttackSpeed(poke.get("baseSpeed").asInt())
                        .setBaseHealth(poke.get("baseMaxHealth").asInt())
                        .setDefencePoints(poke.get("baseDefense").asInt())
                        .setLevel(poke.get("level").asInt())
                        .setSkills(selectSkills(poke.get("skills")))
                        .build();

                // Guardamos Pokemon en el node en Hash de Pokemons
                pokeHash.put(poke.get("ID").asInt(), pokemon);

            }

        } else {
            throw new InvalidDataException("pokemon.json");
        }

        return pokeHash;
    }


}
