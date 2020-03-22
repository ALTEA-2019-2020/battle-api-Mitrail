package pokemon_battle_api.manager;


import lombok.Data;
import org.springframework.stereotype.Component;
import pokemon_battle_api.entity.Battle;
import pokemon_battle_api.entity.Pokemon;
import pokemon_battle_api.entity.Stats;
import pokemon_battle_api.entity.Trainer;
import pokemon_battle_api.exception.BusinessException;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Data
public class BattleManager {

    private Map<UUID, Battle> battleList = new HashMap<>();

    public UUID createBattle(Trainer trainer1, Trainer trainer2) {
        List<Pokemon> pokemonsTrainer1 = trainer1.getTeam().stream().map(BattleManager::getPokemonWithStats).collect(Collectors.toList());
        List<Pokemon> pokemonsTrainer2 = trainer2.getTeam().stream().map(BattleManager::getPokemonWithStats).collect(Collectors.toList());
        trainer1.setNextTurn(true); /* TODO : pas compris la règle de gestion */
        trainer1.setTeam(pokemonsTrainer1);
        trainer2.setTeam(pokemonsTrainer2);
        Battle battle = Battle.builder()
                .trainer(trainer1)
                .opponent(trainer2)
                .build();
        UUID uuid = UUID.randomUUID();
        battle.setUuid(uuid);
        battleList.put(uuid, battle);
        return uuid;
    }

    public Battle getBattleState(UUID uuid) {
        return battleList.get(uuid);
    }

    public Battle attackOnBattle(UUID uuid, String trainerName) throws BusinessException {
        return processBattle(this.getBattleState(uuid), trainerName);
    }

    public static Pokemon getPokemonWithStats(Pokemon pokemon) {
        Integer level = pokemon.getLevel();
        Stats defaultStats = pokemon.getPokemonType().getStats();

        pokemon.setHp(calcRealHp(defaultStats.getHp(), level));
        pokemon.setAttack(calcRealStats(defaultStats.getAttack(), level));
        pokemon.setDefense(calcRealStats(defaultStats.getDefense(), level));
        pokemon.setSpeed(calcRealStats(defaultStats.getSpeed(), level));

        return pokemon;
    }

    public static Integer calcRealStats(Integer baseStat, Integer level) {
        return 5 + (baseStat * (level / 50));
    }

    public static Integer calcRealHp(Integer hp, Integer level) {
        return 10 + level + (hp * (level / 50));
    }

    public Battle processBattle(Battle battle, String trainerName) throws BusinessException {
        Trainer attacker = battle.getTrainer().getName().equals(trainerName) ? battle.getTrainer() : battle.getOpponent();
        Trainer defender = battle.getTrainer().getName().equals(trainerName) ? battle.getOpponent() : battle.getTrainer();
        if (!attacker.isNextTurn()) throw new BusinessException();

        Pokemon pokemonAttacker = attacker.getTeam().stream().filter(pokemon -> !pokemon.isKo()).findFirst().orElse(null);
        Pokemon pokemonDefender = defender.getTeam().stream().filter(pokemon -> !pokemon.isKo()).findFirst().orElse(null);

        if (pokemonAttacker == null) throw new BusinessException();
        if (pokemonDefender == null) throw new BusinessException();

        Integer lvlAttacker = pokemonAttacker.getLevel();
        Integer powerAttack = pokemonAttacker.getAttack();
        Integer powerDefense = pokemonDefender.getDefense();
        Integer hpDefender = pokemonDefender.getHp();

        Integer hpToLose = (((2 * lvlAttacker / 5) + (2 * (powerAttack / powerDefense))) + 2);
        pokemonDefender.setHp(hpDefender - hpToLose);

        if (pokemonDefender.getHp() < 0) {
            pokemonDefender.setKo(true);
        }
        return battle;
    }
}