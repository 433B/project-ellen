package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.*;

public class Backpack implements ActorContainer<Collectible> {
    private int capacity;
    private String containerName;
    private List<Collectible> backpackItems = new ArrayList<>();
//    private Collectible[] backpack;

    public Backpack(String name, int capacity) {
        this.containerName = name;
        this.capacity = capacity;
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(backpackItems);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return backpackItems.size();
    }

    @Override
    public @NotNull String getName() {
        return containerName;
    }

    @Override
    public void add(@NotNull Collectible actor) {

        if (backpackItems.size() < getCapacity()) {
            backpackItems.add(actor);
        }
        else {
            throw new IllegalStateException(getName() + "is full");
        }
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if (backpackItems != null) {
            backpackItems.remove(actor);
        }
    }

    @Override
    public @Nullable Collectible peek() {
        if (getSize() > 0) {
            return backpackItems.get(getSize() - 1);
        }
        else {
            return null;
        }
    }

    @Override
    public void shift() {
        Collections.rotate(backpackItems, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
//        for (Collectible item : backpack) {
//            item.equals(backpack);
//            Arrays.stream(backpack).iterator().next();
//            Arrays.stream(backpack).iterator().hasNext();
//        }
        return backpackItems.iterator();
    }
}
