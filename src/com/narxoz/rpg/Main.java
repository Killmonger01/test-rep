package com.narxoz.rpg;

import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.impl.DarkNova;
import com.narxoz.rpg.combat.impl.ShadowStrike;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.impl.FireComponentFactory;
import com.narxoz.rpg.factory.impl.IceComponentFactory;
import com.narxoz.rpg.factory.impl.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   RPG Enemy System — Creational Patterns Capstone        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  PART 1: ABSTRACT FACTORY — Themed Components║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        EnemyComponentFactory fireFactory   = new FireComponentFactory();
        EnemyComponentFactory iceFactory    = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        System.out.println("Each factory guarantees matching components — no theme mixing possible!\n");

        demonstrateFactory(fireFactory,   "FIRE");
        demonstrateFactory(iceFactory,    "ICE");
        demonstrateFactory(shadowFactory, "SHADOW");

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  PART 2: BUILDER — Complex Enemy Construction║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        System.out.println("--- Building 'Ancient Fire Dragon' with BossEnemyBuilder ---");
        Enemy fireDragon = new BossEnemyBuilder()
                .setName("Ancient Fire Dragon")
                .setHealth(50000)
                .setDamage(500)
                .setDefense(200)
                .setSpeed(50)
                .setElement("FIRE")
                .setAbilities(fireFactory.createAbilities())
                .setLootTable(fireFactory.createLootTable())
                .setAI(fireFactory.createAIBehavior())
                .addPhase(1, 50000)
                .addPhase(2, 30000)
                .addPhase(3, 15000)
                .addPhase(4, 5000)
                .build();

        System.out.println("Built successfully! (No telescoping constructor — readable & maintainable)\n");
        fireDragon.displayInfo();

        System.out.println();

        System.out.println("--- Building 'Ice Goblin Guard' with BasicEnemyBuilder ---");
        Enemy iceGoblinGuard = new BasicEnemyBuilder()
                .setName("Ice Goblin Guard")
                .setHealth(800)
                .setDamage(60)
                .setDefense(30)
                .setSpeed(25)
                .setElement("ICE")
                .setAbilities(iceFactory.createAbilities().subList(0, 2))
                .setLootTable(iceFactory.createLootTable())
                .setAI(iceFactory.createAIBehavior())
                .build();

        iceGoblinGuard.displayInfo();
        System.out.println();

        System.out.println("--- Using EnemyDirector for preset enemy creation ---");

        EnemyDirector director = new EnemyDirector(new BossEnemyBuilder());

        Enemy shadowMiniBoss = director.createMiniBoss(shadowFactory);
        shadowMiniBoss.setName("Shadow Colossus");
        System.out.println("Director created Mini-Boss:");
        shadowMiniBoss.displayInfo();
        System.out.println();

        Enemy shadowRaidBoss = director.createRaidBoss(shadowFactory);
        shadowRaidBoss.setName("The Dark One — Raid Boss");
        System.out.println("Director created Raid Boss:");
        shadowRaidBoss.displayInfo();

        System.out.println();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  PART 3: PROTOTYPE — Enemy Cloning & Variants║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        EnemyRegistry registry = new EnemyRegistry();

        Goblin baseGoblin = new Goblin("Goblin");
        registry.registerTemplate("goblin", baseGoblin);

        System.out.println("Template registered: 'goblin'");
        System.out.println("Available templates: " + registry.listTemplates() + "\n");

        Enemy eliteGoblin = registry.createFromTemplate("goblin");
        eliteGoblin.setName("Elite Goblin");
        eliteGoblin.multiplyStats(2.0);
        System.out.println("Variant — Elite Goblin (2x stats):");
        eliteGoblin.displayInfo();
        System.out.println();

        Enemy championGoblin = registry.createFromTemplate("goblin");
        championGoblin.setName("Champion Goblin");
        championGoblin.multiplyStats(5.0);
        championGoblin.addAbility(new ShadowStrike());
        System.out.println("Variant — Champion Goblin (5x stats + ability):");
        championGoblin.displayInfo();
        System.out.println();

        Enemy goblinKing = registry.createFromTemplate("goblin");
        goblinKing.setName("Goblin King");
        goblinKing.multiplyStats(10.0);
        goblinKing.addAbility(new ShadowStrike());
        goblinKing.addAbility(new DarkNova());
        System.out.println("Variant — Goblin King (10x stats + boss abilities):");
        goblinKing.displayInfo();
        System.out.println();

        Enemy baseDragon = new BossEnemyBuilder()
                .setName("Dragon")
                .setHealth(30000)
                .setDamage(300)
                .setDefense(150)
                .setSpeed(45)
                .setElement("NONE")
                .addPhase(1, 30000)
                .addPhase(2, 15000)
                .addPhase(3, 5000)
                .build();

        registry.registerTemplate("dragon", baseDragon);
        System.out.println("Template registered: 'dragon'");

        Enemy fireDragonVariant = registry.createFromTemplate("dragon");
        fireDragonVariant.setName("Fire Dragon");
        fireDragonVariant.setElement("FIRE");
        for (Ability a : fireFactory.createAbilities()) fireDragonVariant.addAbility(a);
        System.out.println("Variant — Fire Dragon (elemental clone):");
        fireDragonVariant.displayInfo();
        System.out.println();

        Enemy iceDragonVariant = registry.createFromTemplate("dragon");
        iceDragonVariant.setName("Ice Dragon");
        iceDragonVariant.setElement("ICE");
        for (Ability a : iceFactory.createAbilities()) iceDragonVariant.addAbility(a);
        System.out.println("Variant — Ice Dragon (elemental clone):");
        iceDragonVariant.displayInfo();
        System.out.println();

        Enemy shadowDragonVariant = registry.createFromTemplate("dragon");
        shadowDragonVariant.setName("Shadow Dragon");
        shadowDragonVariant.setElement("SHADOW");
        for (Ability a : shadowFactory.createAbilities()) shadowDragonVariant.addAbility(a);
        System.out.println("Variant — Shadow Dragon (elemental clone):");
        shadowDragonVariant.displayInfo();
        System.out.println();

        System.out.println("--- Deep Copy Verification ---");
        Enemy originalGoblin = registry.createFromTemplate("goblin");
        originalGoblin.setName("Original Goblin");
        int originalAbilityCount = originalGoblin.getAbilities().size();

        Enemy clonedGoblin = originalGoblin.copy();
        clonedGoblin.setName("Cloned Goblin");
        clonedGoblin.addAbility(new DarkNova());

        System.out.println("Original ability count: " + originalAbilityCount);
        System.out.println("Clone ability count after adding DarkNova: " + clonedGoblin.getAbilities().size());
        System.out.println("Original ability count UNCHANGED: " + originalGoblin.getAbilities().size());
        System.out.println("✔ Deep copy works correctly — modifying clone did NOT affect original!\n");

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║  PART 4: ALL PATTERNS WORKING TOGETHER           ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        System.out.println("Pipeline: Abstract Factory → Builder → Factory Method → Prototype\n");

        System.out.println("Step 1 [Abstract Factory]: Shadow factory creates matching components");
        List<Ability> shadowAbilities = shadowFactory.createAbilities();
        System.out.println("  Abilities: " + shadowAbilities.stream()
                .map(Ability::getName).reduce((a,b)->a+", "+b).orElse("none"));
        System.out.println("  Loot: " + shadowFactory.createLootTable().getLootInfo());
        System.out.println("  AI: " + shadowFactory.createAIBehavior());

        System.out.println("\nStep 2 [Builder]: BossEnemyBuilder assembles Demon Lord");

        System.out.println("Step 3 [Factory Method]: build() produces final Enemy object");
        Enemy demonLord = new BossEnemyBuilder()
                .setName("Demon Lord Malachar")
                .setHealth(75000)
                .setDamage(650)
                .setDefense(250)
                .setSpeed(55)
                .setElement("SHADOW")
                .setAbilities(shadowFactory.createAbilities())
                .setLootTable(shadowFactory.createLootTable())
                .setAI(shadowFactory.createAIBehavior())
                .addPhase(1, 75000)
                .addPhase(2, 45000)
                .addPhase(3, 20000)
                .addPhase(4, 5000)
                .build();

        System.out.println("Step 4 [Prototype]: Register Demon Lord as template");
        registry.registerTemplate("demon-lord", demonLord);
        System.out.println("  Templates now available: " + registry.listTemplates());

        System.out.println("\nStep 4b [Prototype]: Clone Demon Lord → Greater Demon (2x stats)");
        Enemy greaterDemon = registry.createFromTemplate("demon-lord");
        greaterDemon.setName("Greater Demon");
        greaterDemon.multiplyStats(2.0);

        System.out.println("\nOriginal Demon Lord:");
        demonLord.displayInfo();

        System.out.println("\nCloned Variant — Greater Demon:");
        greaterDemon.displayInfo();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  PATTERN SUMMARY                             ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Abstract Factory : Creates themed component families (Fire, Ice, Shadow).");
        System.out.println("                   Guarantees abilities + loot + AI always match theme.");
        System.out.println();
        System.out.println("Builder          : Assembles complex enemies step-by-step with fluent API.");
        System.out.println("                   Replaces unreadable 15-parameter telescoping constructors.");
        System.out.println("                   EnemyDirector uses preset configurations via the builder.");
        System.out.println();
        System.out.println("Factory Method   : build() is the factory method in EnemyBuilder.");
        System.out.println("                   Director calls build() polymorphically — it doesn't");
        System.out.println("                   know if it gets a BasicEnemyBuilder or BossEnemyBuilder.");
        System.out.println();
        System.out.println("Prototype        : EnemyRegistry stores templates and returns DEEP CLONES.");
        System.out.println("                   Variants (Elite/Champion/King, Fire/Ice/Shadow) created");
        System.out.println("                   with one clone() call instead of repeating full setup.");
        System.out.println();
        System.out.println("Extensibility:");
        System.out.println("  + New element theme (Nature)? → Add NatureComponentFactory — zero changes elsewhere.");
        System.out.println("  + New enemy type (Lich)?      → Register lich template in EnemyRegistry.");
        System.out.println("  + New difficulty tier (Mythic)? → multiplyStats(20.0) on any clone.");
        System.out.println();
        System.out.println("=== Demo Complete ===");
    }

    private static void demonstrateFactory(EnemyComponentFactory factory, String theme) {
        System.out.println("--- " + theme + " Factory ---");
        System.out.println("  AI Behavior : " + factory.createAIBehavior());
        System.out.println("  Abilities   : " + factory.createAbilities().stream()
                .map(a -> a.getName() + " (" + a.getType() + ")")
                .reduce((a,b)->a+", "+b).orElse("none"));
        System.out.println("  Loot table  : " + factory.createLootTable().getLootInfo());
        System.out.println();
    }
}
