package pokemon_battle_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Battle {
    private UUID uuid;
    private Trainer trainer;
    private Trainer opponent;
}