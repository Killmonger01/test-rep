package com.narxoz.rpg.factory.impl;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.impl.FlameBreath;
import com.narxoz.rpg.combat.impl.FireShield;
import com.narxoz.rpg.combat.impl.MeteorStorm;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.impl.FireLootTable;

import java.util.Arrays;
import java.util.List;

public class FireComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(new FlameBreath(), new FireShield(), new MeteorStorm());
    }

    @Override
    public LootTable createLootTable() {
        return new FireLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "AGGRESSIVE";
    }
}
