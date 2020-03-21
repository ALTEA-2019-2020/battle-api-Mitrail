package pokemon_battle_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pokemon_battle_api.dto.BattleDto;
import pokemon_battle_api.entity.Battle;
import pokemon_battle_api.exception.BusinessException;
import pokemon_battle_api.manager.BattleManager;
import pokemon_battle_api.service.TrainerPokemonService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/battles")
@AllArgsConstructor
public class BattleController {

    private BattleManager battleManager;
    private TrainerPokemonService trainerPokemonService;

    @GetMapping
    public List<Battle> getAllBattle() {
        return new ArrayList<>(battleManager.getBattleList().values());
    }

    @GetMapping("/{uuid}")
    public Battle getBattleByUUID(@PathVariable UUID uuid) {
        return battleManager.getBattleState(uuid);
    }

    @PostMapping
    public UUID setNewBattle(@RequestBody BattleDto battleDto) {
        return battleManager.createBattle(trainerPokemonService.getTrainers(battleDto.getTrainer()), trainerPokemonService.getTrainers(battleDto.getOpponent()));
    }

    @PostMapping("/{uuid}/{trainerName}/attack")
    public Battle attack(@PathVariable UUID uuid, @PathVariable String trainerName) throws BusinessException {
        return battleManager.attackOnBattle(uuid, trainerName);
    }

}