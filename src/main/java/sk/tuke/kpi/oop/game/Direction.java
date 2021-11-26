package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1),
    NORTHWEST(-1, 1),
    NORTHEAST(1, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    SOUTHWEST(-1, -1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    NONE(0, 0);

    private final int dx;
    private final int dy;
    private float gradusUhol;

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public float getAngle() {
        NORTH.gradusUhol = 0;
        NORTHWEST.gradusUhol = 45;
        WEST.gradusUhol = 90;
        SOUTHWEST.gradusUhol = 135;
        SOUTH.gradusUhol = 180;
        SOUTHEAST.gradusUhol = 225;
        EAST.gradusUhol = 270;
        NORTHEAST.gradusUhol = 315;
        return gradusUhol;
    }

    public static Direction fromAngle(float angle) {
        if (angle == 0) {
            return Direction.NORTH;
        }
        if (angle == 45) {
            return Direction.NORTHWEST;
        }
        else if (angle == 90) {
            return Direction.WEST;
        }
        else if (angle == 135) {
            return Direction.SOUTHWEST;
        }
        else if (angle == 180) {
            return Direction.SOUTH;
        }
        else if (angle == 225) {
            return Direction.SOUTHEAST;
        }
        else if (angle == 270) {
            return Direction.EAST;
        }
        else if (angle == 315) {
            return Direction.NORTHEAST;
        }
        else {
            return Direction.NONE;
        }
    }


    public Direction combine(Direction other) {
        Direction direction = NONE;
        int moveX, moveY;

        if (this != other) {
            if (getDx() == other.getDx())
                moveX = getDx();
            else
                moveX = getDx() + other.getDx();

            if (getDy() == other.getDy())
                moveY = getDy();
            else
                moveY = getDy() + other.getDy();

            for (Direction value : Direction.values()) {
                if (value.getDx() == moveX && value.getDy() == moveY) {
                    direction = value;
                }
            }
            return direction;
        }
        return other;
    }
}
