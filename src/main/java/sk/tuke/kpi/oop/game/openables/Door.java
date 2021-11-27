package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Door extends AbstractActor implements Usable<Actor>, Openable {
    private Animation openDoorAnimation;
    private Animation closeDoorAnimation;

    private boolean isOpen;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    private List<MapTile> listTiles;
    MapTile.Type mapTile;

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public Door() {
        this.isOpen = false;
        this.listTiles = null;

        openDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
        closeDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
        setAnimation(closeDoorAnimation);
    }

    public Door(String name, Orientation orientation) {
        super(name);
        this.isOpen = false;
        this.listTiles = null;

        if (orientation == Orientation.VERTICAL) {
            openDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            closeDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
            setAnimation(new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        } else {
            openDoorAnimation = new Animation("sprites/hdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            closeDoorAnimation = new Animation("sprites/hdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
            setAnimation(new Animation("sprites/hdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        }
    }

    @Override
    public void useWith(Actor actor) {
        if (isOpen) {
            close();
        } else {
            open();
        }
    }

    @Override
    public void open() {
        if (!isOpen()) {
            isOpen = true;
            setAnimation(openDoorAnimation);
            Objects.requireNonNull(getScene()).getMessageBus().publish(DOOR_OPENED, this);

            if (isOpen()) {
                mapTile = MapTile.Type.CLEAR;
            } else {
                mapTile = MapTile.Type.WALL;
            }
            for (MapTile typeTile : searchOwnTiles()) {
                typeTile.setType(mapTile);
            }
        }
    }

    @Override
    public void close() {
        if (isOpen()) {
            isOpen = false;
            setAnimation(closeDoorAnimation);
            Objects.requireNonNull(getScene()).getMessageBus().publish(DOOR_CLOSED, this);

            if (!isOpen() ) {
                mapTile = MapTile.Type.WALL;
            } else {
                mapTile = MapTile.Type.CLEAR;
            }
            for (MapTile typeTile : searchOwnTiles()) {
                typeTile.setType(mapTile);
            }
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (isOpen()) {
            mapTile = MapTile.Type.CLEAR;
        } else {
            mapTile = MapTile.Type.WALL;
        }

        for (MapTile typeTile : searchOwnTiles()) {
            typeTile.setType(mapTile);
        }
    }

    private List<MapTile> searchOwnTiles() {
        if (listTiles != null) {
            return listTiles;
        }
        List<MapTile> foundTiles = new ArrayList<>();

        int i = 0;
        while (i < getAnimation().getWidth() / Objects.requireNonNull(getScene()).getMap().getTileWidth()) {
             int j = 0;
             while (j < getAnimation().getHeight() / getScene().getMap().getTileHeight()) {
                MapTile typeTile = getScene().getMap().getTile(getPosX() / getScene().getMap().getTileWidth() + i,
                    getPosY() / getScene().getMap().getTileHeight() + j
                );
                foundTiles.add(typeTile);
                j++;
            }
            i++;
        }
        listTiles = foundTiles;
        return listTiles;
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }
}
