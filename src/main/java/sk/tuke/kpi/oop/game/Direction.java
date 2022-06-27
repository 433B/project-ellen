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
    private float angle;

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
        NORTH.angle = 0;
        NORTHWEST.angle = 45;
        WEST.angle = 90;
        SOUTHWEST.angle = 135;
        SOUTH.angle = 180;
        SOUTHEAST.angle = 225;
        EAST.angle = 270;
        NORTHEAST.angle = 315;

        return angle;
    }

    public static Direction fromAngle(float angle) {
        switch ((int) angle) {
            case 0:
                return NORTH;
            case 45:
                return NORTHWEST;
            case 90:
                return WEST;
            case 135:
                return SOUTHWEST;
            case 180:
                return SOUTH;
            case 225:
                return SOUTHEAST;
            case 270:
                return EAST;
            case 315:
                return NORTHEAST;
            default:
                return NONE;
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
