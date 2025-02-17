package com.narxoz.rpg.factory.impl;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.impl.ShadowStrike;
import com.narxoz.rpg.combat.impl.Vanish;
import com.narxoz.rpg.combat.impl.DarkNova;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.impl.ShadowLootTable;

import java.util.Arrays;
import java.util.List;

public class ShadowComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(new ShadowStrike(), new Vanish(), new DarkNova());
    }

    @Override
    public LootTable createLootTable() {
        return new ShadowLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "TACTICAL";
    }
}
