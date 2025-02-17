package com.narxoz.rpg.enemy.impl;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseEnemy implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;
    private String aiBehavior;

    private List<Ability> abilities;
    private LootTable lootTable;

    private Map<Integer, Integer> phases;

    private boolean isBoss;

    public BaseEnemy() {
        this.abilities = new ArrayList<>();
        this.phases    = new HashMap<>();
        this.element   = "NONE";
        this.aiBehavior = "PASSIVE";
        this.isBoss    = false;
    }

    public void setName(String name)          { this.name = name; }
    public void setHealth(int health)         { this.health = health; }
    public void setDamage(int damage)         { this.damage = damage; }
    public void setDefense(int defense)       { this.defense = defense; }
    public void setSpeed(int speed)           { this.speed = speed; }
    public void setElement(String element)    { this.element = element; }
    public void setAiBehavior(String ai)      { this.aiBehavior = ai; }
    public void setAbilities(List<Ability> a) { this.abilities = new ArrayList<>(a); }
    public void setLootTable(LootTable l)     { this.lootTable = l; }
    public void setPhases(Map<Integer,Integer> p) { this.phases = new HashMap<>(p); }
    public void setBoss(boolean boss)         { this.isBoss = boss; }
    public void addPhase(int phase, int threshold) { phases.put(phase, threshold); }

    @Override public String       getName()     { return name; }
    @Override public int          getHealth()   { return health; }
    @Override public int          getDamage()   { return damage; }
    @Override public int          getDefense()  { return defense; }
    @Override public int          getSpeed()    { return speed; }
    @Override public List<Ability> getAbilities() { return new ArrayList<>(abilities); }
    @Override public LootTable    getLootTable() { return lootTable; }

    @Override
    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    @Override
    public void multiplyStats(double multiplier) {
        this.health  = (int)(this.health  * multiplier);
        this.damage  = (int)(this.damage  * multiplier);
        this.defense = (int)(this.defense * multiplier);
        this.speed   = Math.min((int)(this.speed * Math.sqrt(multiplier)), 100);
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + (isBoss ? " [BOSS]" : "") + " ===");
        System.out.printf("  Element: %-8s  AI: %s%n", element, aiBehavior);
        System.out.printf("  HP: %-6d  DMG: %-5d  DEF: %-4d  SPD: %d%n",
                health, damage, defense, speed);
        System.out.println("  Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.printf("    [%-8s] %-18s (DMG: %d) — %s%n",
                    a.getType(), a.getName(), a.getDamage(), a.getDescription());
        }
        if (!phases.isEmpty()) {
            System.out.println("  Boss Phases:");
            for (Map.Entry<Integer,Integer> e : phases.entrySet()) {
                System.out.println("    Phase " + e.getKey() + " activates at " + e.getValue() + " HP");
            }
        }
        if (lootTable != null) {
            System.out.println("  Loot:");
            System.out.println(lootTable.getLootInfo());
        }
    }

    @Override
    public Enemy copy() {
        BaseEnemy copy = new BaseEnemy();
        copy.name       = this.name;
        copy.health     = this.health;
        copy.damage     = this.damage;
        copy.defense    = this.defense;
        copy.speed      = this.speed;
        copy.element    = this.element;
        copy.aiBehavior = this.aiBehavior;
        copy.isBoss     = this.isBoss;

        copy.abilities = new ArrayList<>();
        for (Ability a : this.abilities) {
            copy.abilities.add(a.copy());
        }

        copy.lootTable = (this.lootTable != null) ? this.lootTable.copy() : null;

        copy.phases = new HashMap<>(this.phases);

        return copy;
    }

    @Override
    public String toString() {
        return name + " (HP:" + health + " DMG:" + damage + ")";
    }
}
