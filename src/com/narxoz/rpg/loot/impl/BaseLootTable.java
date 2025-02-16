package com.narxoz.rpg.loot.impl;

import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseLootTable implements LootTable {
    protected List<String> items;
    protected int goldDrop;
    protected int experienceDrop;

    protected BaseLootTable(List<String> items, int goldDrop, int experienceDrop) {
        this.items = new ArrayList<>(items);
        this.goldDrop = goldDrop;
        this.experienceDrop = experienceDrop;
    }

    @Override public List<String> getItems()       { return new ArrayList<>(items); }
    @Override public int          getGoldDrop()     { return goldDrop; }
    @Override public int          getExperienceDrop() { return experienceDrop; }

    @Override
    public String getLootInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("  Gold: ").append(goldDrop)
          .append(" | XP: ").append(experienceDrop)
          .append("\n  Items: ");
        sb.append(String.join(", ", items));
        return sb.toString();
    }
}
