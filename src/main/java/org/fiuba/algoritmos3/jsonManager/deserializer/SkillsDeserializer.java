package org.fiuba.algoritmos3.jsonManager.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.underscore.U;
import org.fiuba.algoritmos3.errors.InvalidDataException;
import org.fiuba.algoritmos3.jsonManager.JsonManager;
import org.fiuba.algoritmos3.jsonManager.JsonPath;
import org.fiuba.algoritmos3.models.pokemon.skills.*;
import org.fiuba.algoritmos3.models.pokemon.status.*;

import java.util.HashMap;

import static org.fiuba.algoritmos3.Constants.MAX_SKILL_USAGE;

public class SkillsDeserializer {

    private final HashMap<Integer, ConcreteSkill> skillsIndex;

    public SkillsDeserializer() throws InvalidDataException {
        JsonNode jsonNode = new JsonManager()
                .Reader(JsonPath.SKILLS.toString())
                .getNode();
        this.skillsIndex = this.use(jsonNode);
    }

    public HashMap<Integer, ConcreteSkill> getSkills() {
        return skillsIndex;
    }

    private static Status getStatus(String stat) {
        return switch (stat) {
            case "Asleep" -> new AsleepStatus();
            case "Poisoned" -> new PoisonedStatus();
            case "Paralyzed" -> new ParalyzedStatus();
            case "Confused" -> new ConfusedStatus();
            case "Dead" -> new DeadStatus();
            default -> throw new IllegalStateException("Unexpected value: " + stat);
        };
    }

    private ConcreteSkill createSkill(JsonNode skill) throws InvalidDataException {

        String cat = skill.get("category").asText();
        String skillName = skill.get("name").asText();

        return switch (cat) {
            case "Attack" -> new AttackSkill(skillName, skill.get("power").asInt(), U.random(MAX_SKILL_USAGE));
            case "Buff" ->
                    new BuffSkill(skillName, StatType.valueOf(skill.get("attribute").asText().toUpperCase()), skill.get("percentage").asInt());
            case "Status" -> new StatusSkill(skillName, getStatus(skill.get("attribute").asText()));
            default -> throw new InvalidDataException("skills.json");
        };

    }

    private HashMap<Integer, ConcreteSkill> use(JsonNode jsonNode) throws InvalidDataException {

        if (jsonNode != null && jsonNode.isArray()) {

            HashMap<Integer, ConcreteSkill> skillHash = new HashMap<>();
            for (JsonNode skill : jsonNode) {
                skillHash.put(skill.get("id").asInt(), createSkill(skill));
            }
            return skillHash;

        } else {
            throw new InvalidDataException("pokemon.json");
        }

    }
}
