package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private int now;
    private final int max;
    private final List<ExhaustionEffect> effectsHealth;

    public Health(int currentHealth, int maxHealth) {
        this.now = currentHealth;
        this.max = maxHealth;
        this.effectsHealth = new ArrayList<>();
    }

    public Health(int health) {
        this.now = health;
        this.max = health;
        this.effectsHealth = new ArrayList<>();
    }

    public int getValue() {
        return now;
    }

    public void refill(int amount) {
        if (now + amount <= max) {
            now += amount;
        } else {
            this.now = this.max;
        }
    }

    public void restore() {
        this.now = this.max;
    }

    public void drain(int amount) {
        if (now != 0) {
            if (now > amount)
                now -= amount;
            else exhaust();
        }

    }

    public void exhaust() {
        if (now != 0) {
            now = 0;

            if (effectsHealth != null) {
                effectsHealth.forEach(ExhaustionEffect::apply);
            }
        }
    }

    public void onExhaustion(ExhaustionEffect effect) {
        this.effectsHealth.add(effect);
    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }
}
