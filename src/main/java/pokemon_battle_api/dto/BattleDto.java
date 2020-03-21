
package pokemon_battle_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BattleDto {
    String trainer;
    String opponent;
}