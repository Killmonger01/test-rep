package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnemyRegistry {

    private final Map<String, Enemy> templates = new HashMap<>();

    public void registerTemplate(String key, Enemy template) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Template key cannot be null or empty.");
        }

        templates.put(key, template.copy());
    }

    public Enemy createFromTemplate(String key) {
        Enemy template = templates.get(key);
        if (template == null) {
            throw new IllegalArgumentException("No template registered with key: '" + key + "'");
        }

        return template.copy();
    }

    public Set<String> listTemplates() {
        return templates.keySet();
    }

    public boolean hasTemplate(String key) {
        return templates.containsKey(key);
    }
}
