package com.narxoz.rpg.loot.impl;

import com.narxoz.rpg.loot.LootTable;

import java.util.Arrays;

public class DefaultLootTable extends BaseLootTable {
    public DefaultLootTable() {
        super(Arrays.asList("Copper Coin", "Torn Cloth", "Bone Fragment"), 50, 80);
    }
    @Override public LootTable copy() {
        return new DefaultLootTable();
    }
}
