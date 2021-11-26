package sk.tuke.kpi.oop.game.characters;

import java.util.HashSet;
import java.util.Set;

public class Health {
    private int max;
    private int now;
    private Set<ExhaustionEffect> effectExh;

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public Health() {
        effectExh = new HashSet<>();
    }

    public Health(int nowMaxHealth) {
        now = nowMaxHealth;
        max = nowMaxHealth;
        effectExh = new HashSet<>();
    }

    public Health(int maxHealth, int minHealth) {
        this.max = maxHealth;
        this.now = minHealth;
        effectExh = new HashSet<>();
    }

    public int getValue() {
        return now;
    }

    void refill(int amount) {
        if (amount + now <= max) {
            now = now + amount;
        }
        else restore();
    }

    public void restore() {
        this.now = this.max;
    }

    void drain(int amount) {
        if (amount < now) {
            now -= amount;
        }
        else
            exhaust();
        onExhaustion(this::exhaust);
    }

    void exhaust() {
        if (now > 0) {
            now = 1;
            drain(1);
        }
    }

    public void onExhaustion(ExhaustionEffect effect) {
        effectExh.add(effect);
    }
}
