package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class MeteorStorm extends BaseAbility {
    public MeteorStorm() {
        super("Meteor Storm", 1200, "Calls down a barrage of blazing meteors, devastating an entire area.", "ULTIMATE");
    }
    @Override public Ability copy() { return new MeteorStorm(); }
}
