package pokemon_battle_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trainer {
    private String name;
    private List<Pokemon> team;
    private boolean nextTurn;
}