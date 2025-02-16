package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class Blizzard extends BaseAbility {
    public Blizzard() {
        super("Blizzard", 900, "Summons a raging blizzard that blankets the entire battlefield in ice and snow.", "ULTIMATE");
    }
    @Override public Ability copy() { return new Blizzard(); }
}
