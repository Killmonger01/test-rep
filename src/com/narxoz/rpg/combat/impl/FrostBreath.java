package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class FrostBreath extends BaseAbility {
    public FrostBreath() {
        super("Frost Breath", 350, "Exhales a freezing gust that damages enemies and slows movement by 50%.", "DAMAGE");
    }
    @Override public Ability copy() { return new FrostBreath(); }
}
