package com.narxoz.rpg.loot.impl;

import com.narxoz.rpg.loot.LootTable;

import java.util.Arrays;

public class FireLootTable extends BaseLootTable {
    public FireLootTable() {
        super(Arrays.asList("Fire Gem", "Dragon Scale", "Flame Rune", "Ember Core"), 850, 1200);
    }
    @Override public LootTable copy() {
        return new FireLootTable();
    }
}
