package com.narxoz.rpg.combat.impl;

import com.narxoz.rpg.combat.Ability;

public class ShadowStrike extends BaseAbility {
    public ShadowStrike() {
        super("Shadow Strike", 600, "Emerges from shadows to deliver a devastating blow that blinds the target.", "DAMAGE");
    }
    @Override public Ability copy() { return new ShadowStrike(); }
}
