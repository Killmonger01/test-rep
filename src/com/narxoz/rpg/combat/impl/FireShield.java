package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class FireShield extends BaseAbility {
    public FireShield() {
        super("Fire Shield", 0, "Surrounds self in molten armor, reflecting 20% of incoming damage as fire.", "BUFF");
    }
    @Override public Ability copy() { return new FireShield(); }
}
