package pokemon_battle_api.entity;

import lombok.Data;

@Data
public class Pokemon {
    private PokemonType pokemonType;
    private int level;
    private int pokemonTypeId;

    private Integer speed;
    private Integer defense;
    private Integer attack;
    private Integer hp;

    private boolean ko = false;
    private boolean alive = true;
}