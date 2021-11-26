package sk.tuke.kpi.oop.game.openables;

public class LockedDoor extends Door {
    private boolean lock;

    public LockedDoor() {
//        this.lock = true;
    }

//    private void lock() {
//        lock = true;
//    }

    public void unlock() {
        if (isLocked()) {
            lock = false;
        }
    }

    private boolean isLocked() {
        return lock;
    }
}
