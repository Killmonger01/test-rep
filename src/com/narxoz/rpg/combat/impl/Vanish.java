package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class Vanish extends BaseAbility {
    public Vanish() {
        super("Vanish", 0, "Dissolves into darkness, becoming untargetable and repositioning for a surprise attack.", "DEBUFF");
    }
    @Override public Ability copy() { return new Vanish(); }
}
