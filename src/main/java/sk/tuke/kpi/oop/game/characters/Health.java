package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private int max;
    private int now;
    private List<ExhaustionEffect> effectExh;

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public Health(int nowMaxHealth) {
        this.now = nowMaxHealth;
        this.max = nowMaxHealth;
        effectExh = new ArrayList<>();
    }

    public Health(int maxHealth, int minHealth) {
        this.max = maxHealth;
        this.now = minHealth;
        effectExh = new ArrayList<>();
    }

    public void refill(int amount) {
        this.now = this.now + amount;
        if (this.now > this.max) {
            this.now = this.max;
        }
    }

    public void restore() {
        this.now = this.max;
    }

    public void drain(int amount) {
        if (now != 0 && now > amount) {
            now = now - amount;
        } else {
            exhaust();
        }
    }

    void exhaust() {
        if (now != 0) {
            now = 0;

            if (effectExh != null) {
                effectExh.forEach(ExhaustionEffect::apply);
            }
        }
    }

    public void onExhaustion(ExhaustionEffect effect) {
        effectExh.add(effect);
    }

    public int getValue() {
        return now;
    }
}
