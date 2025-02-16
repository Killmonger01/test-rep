package com.narxoz.rpg.loot.impl;

import com.narxoz.rpg.loot.LootTable;

import java.util.Arrays;

public class ShadowLootTable extends BaseLootTable {
    public ShadowLootTable() {
        super(Arrays.asList("Shadow Gem", "Dark Essence", "Shadow Rune", "Void Shard"), 1000, 1500);
    }
    @Override public LootTable copy() {
        return new ShadowLootTable();
    }
}
