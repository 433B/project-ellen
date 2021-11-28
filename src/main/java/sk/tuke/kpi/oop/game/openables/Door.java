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
    private Animation openDoorVertical;
    private Animation closeDoorVertical;
    private Animation openDoorHorizontal;
    private Animation closeDoorHorizontal;

    private boolean isOpen;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    private List<MapTile> listTiles;
    private MapTile.Type mapTile;
    private Animation.PlayMode playMode;
    private String verticalDoorAnimation = "sprites/vdoor.png";
    private String horizontalDoorAnimation = "sprites/hdoor.png";

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public Door() {
        this.isOpen = false;
        this.listTiles = null;
        this.playMode = null;

        openDoorVertical = new Animation(verticalDoorAnimation, 16, 32, 0.1f, Animation.PlayMode.ONCE);
        closeDoorVertical = new Animation(verticalDoorAnimation, 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
        openDoorHorizontal = new Animation(horizontalDoorAnimation, 16, 32, 0.1f, Animation.PlayMode.ONCE);
        closeDoorHorizontal = new Animation(horizontalDoorAnimation, 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
        if (Orientation.VERTICAL != null) {
            setAnimation(new Animation(verticalDoorAnimation, 16, 32));
        }
        else if (Orientation.HORIZONTAL != null) {
            setAnimation(new Animation(horizontalDoorAnimation, 16, 32));
        }
        getAnimation().stop();
    }

    public Door(String name, Orientation orientation) {
        super(name);
        this.isOpen = false;
        this.listTiles = null;

        if (orientation == Orientation.VERTICAL) {
            openDoorVertical = new Animation(verticalDoorAnimation, 16, 32, 0.1f, Animation.PlayMode.ONCE);
            closeDoorVertical = new Animation(verticalDoorAnimation, 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
            setAnimation(new Animation(verticalDoorAnimation, 16, 32));
            setAnimation(new Animation(verticalDoorAnimation, 16, 32));
            getAnimation().stop();
        } else {
            openDoorHorizontal = new Animation(horizontalDoorAnimation, 16, 32, 0.1f, Animation.PlayMode.ONCE);
            closeDoorHorizontal = new Animation(horizontalDoorAnimation, 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
            setAnimation(new Animation(horizontalDoorAnimation, 16, 32));
            getAnimation().stop();
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
            if (Orientation.VERTICAL != null) {
                setAnimation(openDoorVertical);
            }
            else if (Orientation.HORIZONTAL != null) {
                setAnimation(openDoorHorizontal);
            }
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
            if (Orientation.VERTICAL != null) {
                setAnimation(closeDoorVertical);
            }
            else if (Orientation.HORIZONTAL != null) {
                setAnimation(closeDoorHorizontal);
            }

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
