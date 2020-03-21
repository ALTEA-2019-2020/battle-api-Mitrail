package pokemon_battle_api.entity;

import lombok.Data;

import java.util.List;

@Data
public class PokemonType {
    private int id;
    private int baseExperience;
    private int height;
    private String name;
    private Stats stats;
    private int weight;
    private List<String> types;
}