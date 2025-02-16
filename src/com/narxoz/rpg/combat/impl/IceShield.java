package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class IceShield extends BaseAbility {
    public IceShield() {
        super("Ice Shield", 0, "Encases self in layers of enchanted ice, greatly increasing defense.", "BUFF");
    }
    @Override public Ability copy() { return new IceShield(); }
}
