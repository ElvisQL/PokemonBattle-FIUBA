package org.fiuba.algoritmos3.jsonManager.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import org.fiuba.algoritmos3.jsonManager.JsonManager;
import org.fiuba.algoritmos3.jsonManager.JsonPath;
import org.fiuba.algoritmos3.model.error.InvalidDataException;
import org.fiuba.algoritmos3.model.pokemon.PokemonSpecies;
import org.fiuba.algoritmos3.model.pokemon.PokemonType;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.fiuba.algoritmos3.Constants.SKILLS_LIMIT;

public class PokemonSpeciesDeserializer {

    private List<PokemonSpecies> pokemonSpecies;
    private HashMap<Integer, ConcreteSkill> idSkillMap;

    public PokemonSpeciesDeserializer() {
        try {
            JsonNode jsonNode = new JsonManager()
                    .Reader(JsonPath.POKEMON_SPECIES.toString())
                    .getNode();

            this.idSkillMap = new SkillsDeserializer().getSkills();
            this.pokemonSpecies = this.parsePokemonSpecies(jsonNode);
        } catch (InvalidDataException e) {
            throw new RuntimeException("Wrong JSON format: " + JsonPath.POKEMON_SPECIES);
        }
    }

    public List<PokemonSpecies> getPokemonSpecies() {
        return pokemonSpecies;
    }


    private List<PokemonSpecies> parsePokemonSpecies(JsonNode jsonNode) throws InvalidDataException {
        List<PokemonSpecies> pokemonSpecies = new ArrayList<>();


        if (jsonNode == null || !jsonNode.isArray()) {
            throw new InvalidDataException(JsonPath.POKEMON.toString());
        }

        for (JsonNode poke : jsonNode) {
            pokemonSpecies.add(new PokemonSpecies(
                    poke.get("name").asText(),
                    poke.get("history").asText(),
                    PokemonType.valueOf(poke.get("type").asText()),
                    selectSkills(poke.get("skills"))
            ));
        }

        return pokemonSpecies;
    }

    private ArrayList<ConcreteSkill> selectSkills(JsonNode jsonNode) throws InvalidDataException {
        if (jsonNode == null || !jsonNode.isArray()) {
            throw new InvalidDataException("pokemon.json - INVALID SKILL");
        }

        int maxSkill = SKILLS_LIMIT;
        ArrayList<ConcreteSkill> skillArrayList = new ArrayList<>(SKILLS_LIMIT);
        for (JsonNode skill : jsonNode) {
            if (maxSkill > 0) {
                skillArrayList.add(idSkillMap.get(skill.asInt()));
                maxSkill--;
            }
        }

        return skillArrayList;
    }


}
