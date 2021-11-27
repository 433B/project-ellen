package sk.tuke.kpi.oop.game.openables;

public class LockedDoor extends Door {
    private boolean lock;

    public LockedDoor() {
        super("locked door", Orientation.VERTICAL);
        this.lock = true;
    }

    private void lock() {
        lock = true;
    }

    public void unlock() {
        lock = false;
    }

    private boolean isLocked() {
        return lock;
    }
}
