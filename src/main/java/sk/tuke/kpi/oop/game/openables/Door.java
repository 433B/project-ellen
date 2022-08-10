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
    private final Animation openDoorAnimation;
    private final Animation closeDoorAnimation;

    private boolean isOpen;
    public static final Topic<Door> DOOR_OPENED =
        Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED =
        Topic.create("door closed", Door.class);
    private List<MapTile> listTiles;
    private MapTile.Type mapTile;
    private String doorAnimation;

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public Door() {
        this.isOpen = false;
        this.listTiles = null;
        this.doorAnimation = "sprites/vdoor.png";

        openDoorAnimation = new Animation(doorAnimation,
            16, 32,
            0.1f, Animation.PlayMode.ONCE);

        closeDoorAnimation = new Animation(doorAnimation,
            16, 32,
            0.1f, Animation.PlayMode.ONCE_REVERSED);

        setAnimation(closeDoorAnimation);
    }

    public Door(String name, Orientation orientation) {
        super(name);
        this.isOpen = false;
        this.listTiles = null;

        if (orientation == Orientation.VERTICAL) {
            doorAnimation = "sprites/vdoor.png";

            openDoorAnimation = new Animation(doorAnimation,
                16, 32,
                0.1f, Animation.PlayMode.ONCE);

            closeDoorAnimation = new Animation(doorAnimation,
                16, 32,
                0.1f, Animation.PlayMode.ONCE_REVERSED);

            setAnimation(new Animation(doorAnimation, 16, 32));
            getAnimation().stop();
        } else {
            doorAnimation = "sprites/hdoor.png";
            openDoorAnimation = new Animation(doorAnimation,
                32, 16,
                0.1f, Animation.PlayMode.ONCE);

            closeDoorAnimation = new Animation(doorAnimation,
                32, 16,
                0.1f, Animation.PlayMode.ONCE_REVERSED);

            setAnimation(new Animation(doorAnimation, 32, 16));
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
            setAnimation(openDoorAnimation);

            getScene()
                .getMessageBus()
                .publish(DOOR_OPENED, this);

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

            getScene()
                .getMessageBus()
                .publish(DOOR_CLOSED, this);

            if (!isOpen()) mapTile = MapTile.Type.WALL;
             else mapTile = MapTile.Type.CLEAR;

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

        mapTiles(foundTiles);
        listTiles = foundTiles;
        return listTiles;
    }

    private void mapTiles(List<MapTile> foundTiles) {
        for (int i = 0; i < getAnimation().getWidth() / getScene().getMap().getTileWidth(); i++) {
            for (int j = 0; j < getAnimation().getHeight() / getScene().getMap().getTileHeight(); j++) {
                MapTile typeTile = getScene().getMap().getTile(getPosX()
                        / getScene().getMap().getTileWidth() + i,
                    getPosY() / getScene().getMap().getTileHeight() + j
                );
                foundTiles.add(typeTile);
            }
        }
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
