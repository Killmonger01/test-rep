package com.narxoz.rpg.factory.impl;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.impl.FrostBreath;
import com.narxoz.rpg.combat.impl.IceShield;
import com.narxoz.rpg.combat.impl.Blizzard;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.impl.IceLootTable;

import java.util.Arrays;
import java.util.List;

public class IceComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(new FrostBreath(), new IceShield(), new Blizzard());
    }

    @Override
    public LootTable createLootTable() {
        return new IceLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "DEFENSIVE";
    }
}
