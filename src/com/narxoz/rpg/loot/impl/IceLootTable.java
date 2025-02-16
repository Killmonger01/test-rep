package com.narxoz.rpg.loot.impl;

import com.narxoz.rpg.loot.LootTable;

import java.util.Arrays;

public class IceLootTable extends BaseLootTable {
    public IceLootTable() {
        super(Arrays.asList("Ice Gem", "Frost Scale", "Ice Rune", "Frozen Crystal"), 700, 1000);
    }
    @Override public LootTable copy() {
        return new IceLootTable();
    }
}
