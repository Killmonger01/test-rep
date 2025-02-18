package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.impl.BaseEnemy;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.impl.DefaultLootTable;

import java.util.ArrayList;
import java.util.List;

public class BasicEnemyBuilder implements EnemyBuilder {

    private String       name;
    private int          health  = 100;
    private int          damage  = 15;
    private int          defense = 5;
    private int          speed   = 30;
    private String       element = "NONE";
    private String       ai      = "PASSIVE";
    private List<Ability> abilities = new ArrayList<>();
    private LootTable    lootTable;

    @Override public EnemyBuilder setName(String name)            { this.name = name;       return this; }
    @Override public EnemyBuilder setHealth(int health)           { this.health = health;   return this; }
    @Override public EnemyBuilder setDamage(int damage)           { this.damage = damage;   return this; }
    @Override public EnemyBuilder setDefense(int defense)         { this.defense = defense; return this; }
    @Override public EnemyBuilder setSpeed(int speed)             { this.speed = speed;     return this; }
    @Override public EnemyBuilder setElement(String element)      { this.element = element; return this; }
    @Override public EnemyBuilder setAI(String aiBehavior)        { this.ai = aiBehavior;   return this; }
    @Override public EnemyBuilder addAbility(Ability a)           { abilities.add(a);       return this; }
    @Override public EnemyBuilder setAbilities(List<Ability> a)   { this.abilities = new ArrayList<>(a); return this; }
    @Override public EnemyBuilder setLootTable(LootTable loot)    { this.lootTable = loot;  return this; }

    @Override public EnemyBuilder addPhase(int phase, int threshold) { return this; }

    @Override
    public Enemy build() {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Enemy name is required!");
        }
        if (health <= 0) {
            throw new IllegalStateException("Enemy health must be positive!");
        }

        if (lootTable == null) {
            lootTable = new DefaultLootTable();
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
        enemy.setBoss(false);
        return enemy;
    }
}
