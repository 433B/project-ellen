package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private int max;
    private int now;
    private List<ExhaustionEffect> efects;

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public Health(int healthNow, int healthMax) {
        now = healthNow;
        max = healthMax;
        efects = new ArrayList<>();
    }

    public Health(int healthNow) {
        now = healthNow;
        max = this.now;
        efects = new ArrayList<>();
    }

    public void refill(int amount) {
        if (now + amount <= max)
            now += amount;
        else
            restore();
    }

    public void restore() {
        now = max;
    }

    public void drain(int amount) {
        now -= amount;
        if (now <= 0) {
            now = 0;
            exhaust();
        }
    }

    public void exhaust() {
        if (now != 0) {
            now = 0;
            if (efects != null) {
                efects.forEach(ExhaustionEffect::apply);
            }
        }
    }

    public int getValue() {
        return this.now;
    }

    public void onExhaustion(ExhaustionEffect effect) {
        efects.add(effect);
    }
}
