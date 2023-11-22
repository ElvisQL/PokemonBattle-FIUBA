package org.fiuba.algoritmos3.model.pokemon;


import org.fiuba.algoritmos3.Visitor;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;
import org.fiuba.algoritmos3.model.pokemon.status.DeadStatus;
import org.fiuba.algoritmos3.model.pokemon.status.Status;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pokemon {

    protected PokemonType type;
    protected String history;
    protected Integer level;
    protected List<ConcreteSkill> skills;
    protected HashSet<Status> statuses = new HashSet<>();
    protected Integer health;
    protected Integer attackSpeed;
    protected Integer attackPoints;
    protected Integer defencePoints;
    protected Integer maxHealth;
    protected Integer baseHealth;
    protected String name;
    protected PokemonSpecies species;
    protected Integer ID;

    // BUILD POKEMON -------------------------------------------------------------------------------------------------
    protected Pokemon() {
    }

    // GETTERS --------------------------------------------------------------------------------------------------------
    public PokemonSpecies getSpecies() {
        return species;
    }

    public Integer getAttackSpeed() {
        return attackSpeed;
    }

    public Integer getAttackPoints() {
        return attackPoints;
    }

    public Integer getDefencePoints() {
        return defencePoints;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public List<ConcreteSkill> getSkills() {
        return skills;
    }

    public Integer getLevel() {
        return level;
    }

    public PokemonType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getHealth() {
        return health;
    }


    public Integer getID() {
        return ID;
    }

    // REGULAR SETTERS --------------------------------------------------------------------------------------------------------
    public void setHealth(Integer health) {
        this.health = Math.min(health, this.getMaxHealth());
        if (this.health <= 0) {
            this.kill();
        }
    }

    public void setAttackPoints(int i) {
        this.attackPoints = i;
    }

    public void setDefencePoints(int i) {
        this.defencePoints = i;
    }

    public void setAttackSpeed(int i) {
        this.attackSpeed = i;
    }

    // DYNAMIC METHODS -----------------------------------------------------------------------------------------------

    public Set<Status> getStatuses() {
        return statuses;
    }

    public boolean addStatus(Status status) {
        return statuses.add(status);

    }

    public String getStatusDescription() {
        StringBuilder description = new StringBuilder();
        for (Status status : statuses) {
            description.append(status.getName()).append(" ");
        }
        return description.toString().trim();
    }

    public Status getStatus(Class<? extends Status> strategyClass) {
        Status status = null;
        for (Status strategy : this.statuses) {
            if (strategy.getClass() == strategyClass) {
                status = strategy;
            }
        }
        return status;
    }

    public void clearStatuses() {
        this.statuses.clear();
    }

    public void deleteStatus(Status status) {
        this.statuses.remove(status);
    }

    public boolean hasStatus(Class<? extends Status> statusClass) {
        for (Status strategy : this.statuses) {
            if (statusClass.isInstance(strategy)) {
                return true;
            }
        }
        return false;
    }

    public void kill() {
        this.statuses.clear();
        this.statuses.add(new DeadStatus());
    }

    public void levelUp(Integer up) {
        this.level = this.level + up;
    }

    public boolean isDead() {
        return this.hasStatus(DeadStatus.class);
    }

    // UI -------------------------------------------------------------------------------------------------------------

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}