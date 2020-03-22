package pokemon_battle_api.manager;

import pokemon_battle_api.entity.Battle;
import pokemon_battle_api.entity.Pokemon;
import pokemon_battle_api.entity.Stats;
import pokemon_battle_api.entity.Trainer;
import pokemon_battle_api.exception.BusinessException;

import java.util.*;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class BattleManagerTest {

    @Test
    void testCreateBattle() {
        var trainer1 = new Trainer();
        trainer1.setTeam(new ArrayList<>());
        var trainer2 = new Trainer();
        trainer2.setTeam(new ArrayList<>());
        BattleManager battleManager = new BattleManager();
        UUID uuid = battleManager.createBattle(trainer1, trainer2);
        assertNotNull(uuid);

        Battle battle = battleManager.getBattleState(uuid);
        assertEquals(trainer1, battle.getTrainer());
        assertEquals(trainer2, battle.getOpponent());
    }

    @Test
    void testCalcRealStats_shouldBe5() {
        Integer res = BattleManager.calcRealStats(0,0);
        assertEquals(5, res);
    }

    @Test
    void testCalcRealStats_shouldBe6() {
        Integer res = BattleManager.calcRealStats(1,50);
        assertEquals(6, res);
    }

    @Test
    void testCalcRealHp_shouldBe10() {
        Integer res = BattleManager.calcRealHp(0,0);
        assertEquals(10, res);
    }

    @Test
    void testCalcRealHp_shouldBe61() {
        Integer res = BattleManager.calcRealHp(1,50);
        assertEquals(61, res);
    }
}