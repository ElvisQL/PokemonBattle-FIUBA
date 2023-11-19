package org.fiuba.algoritmos3.game.model.pokemon;

public class PokemonStateData {

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Integer getHealth() {
        return health;
    }

    private Integer id;
    private String status;
    private Integer health;

    public PokemonStateData() {
    }

    public PokemonStateData buildFromPokemon(Pokemon pokemon) {
        return new PokemonStateData()
                .setId(pokemon.getID())
                .setHealth(pokemon.getHealth())
                .setStatus(pokemon.getStatusDescription());
    }

    private PokemonStateData setId(Integer id) {
        this.id = id;
        return this;
    }

    private PokemonStateData setStatus(String status) {
        this.status = status;
        return this;
    }

    private PokemonStateData setHealth(Integer health) {
        this.health = health;
        return this;
    }


}
