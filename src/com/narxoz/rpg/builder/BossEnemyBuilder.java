package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.impl.BaseEnemy;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.impl.FireLootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BossEnemyBuilder implements EnemyBuilder {

    private String            name;
    private int               health   = 10000;
    private int               damage   = 300;
    private int               defense  = 100;
    private int               speed    = 40;
    private String            element  = "NONE";
    private String            ai       = "AGGRESSIVE";
    private List<Ability>     abilities = new ArrayList<>();
    private Map<Integer,Integer> phases = new HashMap<>();
    private LootTable         lootTable;

    @Override public EnemyBuilder setName(String name)            { this.name = name;       return this; }
    @Override public EnemyBuilder setHealth(int health)           { this.health = health;   return this; }
    @Override public EnemyBuilder setDamage(int damage)           { this.damage = damage;   return this; }
    @Override public EnemyBuilder setDefense(int defense)         { this.defense = defense; return this; }
    @Override public EnemyBuilder setSpeed(int speed)             { this.speed = speed;     return this; }
    @Override public EnemyBuilder setElement(String element)      { this.element = element; return this; }
    @Override public EnemyBuilder setAI(String ai)                { this.ai = ai;           return this; }
    @Override public EnemyBuilder addAbility(Ability a)           { abilities.add(a);       return this; }
    @Override public EnemyBuilder setAbilities(List<Ability> a)   { this.abilities = new ArrayList<>(a); return this; }
    @Override public EnemyBuilder setLootTable(LootTable loot)    { this.lootTable = loot;  return this; }

    @Override
    public EnemyBuilder addPhase(int phase, int threshold) {
        phases.put(phase, threshold);
        return this;
    }

    @Override
    public Enemy build() {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Boss name is required!");
        }
        if (health <= 0) {
            throw new IllegalStateException("Boss health must be positive!");
        }

        if (lootTable == null) {
            lootTable = new FireLootTable();
        }

        if (phases.isEmpty()) {
            int p1 = health;
            int p2 = (int)(health * 0.60);
            int p3 = (int)(health * 0.30);
            phases.put(1, p1);
            phases.put(2, p2);
            phases.put(3, p3);
        }

        BaseEnemy enemy = new BaseEnemy();
        enemy.setName(name);
        enemy.setHealth(health);
        enemy.setDamage(damage);
        enemy.setDefense(defense);
        enemy.setSpeed(speed);
        enemy.setElement(element);
        enemy.setAiBehavior(ai);
        enemy.setAbilities(abilities);
        enemy.setLootTable(lootTable);
        enemy.setPhases(phases);
        enemy.setBoss(true);
        return enemy;
    }
}
