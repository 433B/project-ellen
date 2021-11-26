package sk.tuke.kpi.oop.game.characters;

public class Health {
    private int max;
    private int now;


    public Health() {

    }

    public Health(int nowMaxHealth) {

        now = nowMaxHealth;
        max = now;
    }

    public Health(int maxHealth, int minHealth) {
        this.max = maxHealth;
        this.now = minHealth;
    }

    public int getValue() {
        return now;
    }

    public void setValue(int health) {
        this.now = health;
    }

    void refill(int amount) {
        if (amount + now <= max) {
            max += amount;
        }
        else restore();
    }

    void restore() {
        now = max;
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
        now = 0;

    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect) {
        effect.apply();
    }

//    @Override
//    public void addedToScene(@NotNull Scene scene) {
////        super.addedToScene(scene);
////        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
//    }

}
