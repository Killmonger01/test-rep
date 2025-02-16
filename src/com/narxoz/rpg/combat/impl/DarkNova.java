package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class DarkNova extends BaseAbility {
    public DarkNova() {
        super("Dark Nova", 1500, "Unleashes a cataclysmic explosion of void energy that obliterates everything nearby.", "ULTIMATE");
    }
    @Override public Ability copy() { return new DarkNova(); }
}
