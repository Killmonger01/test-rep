package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class FlameBreath extends BaseAbility {
    public FlameBreath() {
        super("Flame Breath", 450, "Exhales a cone of searing flames, burning all enemies in a 60° arc.", "DAMAGE");
    }
    @Override public Ability copy() { return new FlameBreath(); }
}
