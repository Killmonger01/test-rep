package com.narxoz.rpg.combat;

public interface Ability {
    String getName();
    int getDamage();
    String getDescription();
    String getType();
    Ability copy();
}
