package com.narxoz.rpg.loot;

public interface LootTable {
    java.util.List<String> getItems();
    int getGoldDrop();
    int getExperienceDrop();
    String getLootInfo();
    LootTable copy();
}
