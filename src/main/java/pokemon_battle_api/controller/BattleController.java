package pokemon_battle_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Battle> setNewBattleFromParam(@RequestParam String trainer, @RequestParam String opponent) {
        UUID uuid = battleManager.createBattle(trainerPokemonService.getTrainers(trainer), trainerPokemonService.getTrainers(opponent));
        Battle battle = battleManager.getBattleState(uuid);
        return new ResponseEntity<>(battle, HttpStatus.CREATED);
    }

    @PostMapping("/{uuid}/{trainerName}/attack")
    public Battle attack(@PathVariable UUID uuid, @PathVariable String trainerName) throws BusinessException {
        return battleManager.attackOnBattle(uuid, trainerName);
    }

}