package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public abstract class BaseAbility implements Ability {
    protected String name;
    protected int damage;
    protected String description;
    protected String type;

    protected BaseAbility(String name, int damage, String description, String type) {
        this.name = name;
        this.damage = damage;
        this.description = description;
        this.type = type;
    }

    @Override public String getName()        { return name; }
    @Override public int    getDamage()      { return damage; }
    @Override public String getDescription() { return description; }
    @Override public String getType()        { return type; }
}
