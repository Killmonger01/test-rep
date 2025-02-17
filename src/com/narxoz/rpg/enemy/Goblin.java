package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.impl.DefaultLootTable;

import java.util.ArrayList;
import java.util.List;

public class Goblin implements Enemy {

    private String        name;
    private int           health;
    private int           damage;
    private int           defense;
    private int           speed;
    private String        element;
    private String        aiBehavior;
    private List<Ability> abilities;
    private LootTable     lootTable;

    public Goblin(String name) {
        this.name       = name;
        this.health     = 100;
        this.damage     = 15;
        this.defense    = 5;
        this.speed      = 35;
        this.element    = "NONE";
        this.aiBehavior = "PASSIVE";
        this.abilities  = new ArrayList<>();
        this.lootTable  = new DefaultLootTable();
    }

    @Override public String        getName()     { return name; }
    @Override public int           getHealth()   { return health; }
    @Override public int           getDamage()   { return damage; }
    @Override public int           getDefense()  { return defense; }
    @Override public int           getSpeed()    { return speed; }
    @Override public List<Ability> getAbilities(){ return new ArrayList<>(abilities); }
    @Override public LootTable     getLootTable() { return lootTable; }

    @Override public void setName(String name)      { this.name = name; }
    @Override public void setElement(String element){ this.element = element; }
    @Override public void addAbility(Ability a)     { abilities.add(a); }

    @Override
    public void multiplyStats(double multiplier) {
        this.health  = (int)(this.health  * multiplier);
        this.damage  = (int)(this.damage  * multiplier);
        this.defense = (int)(this.defense * multiplier);
        this.speed   = Math.min((int)(this.speed * Math.sqrt(multiplier)), 100);
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (Goblin) ===");
        System.out.printf("  Element: %-8s  AI: %s%n", element, aiBehavior);
        System.out.printf("  HP: %-6d  DMG: %-5d  DEF: %-4d  SPD: %d%n",
                health, damage, defense, speed);
        System.out.println("  Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.printf("    [%-8s] %-18s (DMG: %d)%n",
                    a.getType(), a.getName(), a.getDamage());
        }
        if (lootTable != null) {
            System.out.println("  Loot:");
            System.out.println(lootTable.getLootInfo());
        }
    }

    @Override
    public Enemy copy() {
        Goblin copy = new Goblin(this.name);
        copy.health     = this.health;
        copy.damage     = this.damage;
        copy.defense    = this.defense;
        copy.speed      = this.speed;
        copy.element    = this.element;
        copy.aiBehavior = this.aiBehavior;

        copy.abilities = new ArrayList<>();
        for (Ability a : this.abilities) {
            copy.abilities.add(a.copy());
        }

        copy.lootTable = (this.lootTable != null) ? this.lootTable.copy() : null;

        return copy;
    }
}
