/**
 * Irány tárolására alkalmas osztály
 */

// Debugnal itt a hiba!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// KURVABUGOS


public class Orientation {
    public enum Type {
        NORTH, WEST, SOUTH, EAST
    }

    public static Coordinate getCoordinate (Orientation.Type type) {
        Coordinate coordinate = new Coordinate(0,0);
        switch (type){
            case NORTH:
                coordinate = new Coordinate(-1, 0);
                break;
            case WEST:
                coordinate = new Coordinate(0, -1);
                break;
            case SOUTH:
                coordinate = new Coordinate(1, 0);
                break;
            case EAST:
                coordinate = new Coordinate(0, 1);
                break;

        }
        return coordinate;
    }

    public static Type getOrientationType(Coordinate postion) {
        Type type = Type.NORTH;
        if (postion.equals(new Coordinate(-1, 0)))
            type = Type.NORTH;
        else if (postion.equals(new Coordinate(0, -1)))
            type = Type.WEST;
        else if (postion.equals(new Coordinate(1, 0)))
            type = Type.SOUTH;
        else if (postion.equals(new Coordinate(0, 1)))
            type = Type.EAST;
        return type;
    }

    public static  Type getOpposite(int i) {
        Type direction = Type.NORTH;
        switch (i){
            case 0:
                direction = Type.SOUTH;
                break;
            case 1:
                direction = Type.EAST;
                break;
            case 2:
                direction = Type.NORTH;
                break;
            case 3:
                direction = Type.WEST;
                break;
            default:
                break;
        }
        return direction;
    }

}
