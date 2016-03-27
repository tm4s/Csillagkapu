/**
 * Irány tárolására alkalmas osztály
 */

// Debugnal itt a hiba!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// KURVABUGOS
// elm most csak a Type kell belole mas nem de nembiztos :DD

public class Orientation {
    public enum Type {
        NORTH, WEST, SOUTH, EAST
    }

    public static Coordinate getCoordinate(Orientation.Type type) {
    	Logger.log(">Orientation.getCoordinate(Orientation.Type type)");
        Coordinate coordinate = new Coordinate(0, 0);
        switch (type) {
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
        Logger.log("<Orientation.getCoordinate(Orientation.Type type)");
        return coordinate;
    }

    public static Type getOrientationType(Coordinate position) {
    	Logger.log(">Orientation.getOrientationType(Coordinate position)");
        Type type = Type.NORTH;
        if (position.equals(new Coordinate(-1, 0)))
            type = Type.NORTH;
        else if (position.equals(new Coordinate(0, -1)))
            type = Type.WEST;
        else if (position.equals(new Coordinate(1, 0)))
            type = Type.SOUTH;
        else if (position.equals(new Coordinate(0, 1)))
            type = Type.EAST;
        Logger.log("<Orientation.getOrientationType(Coordinate position)");
        return type;
    }

    public static Type getOpposite(int i) {
    	Logger.log(">Orientation.getOpposite(int i)");
        Type direction = Type.NORTH;
        switch (i) {
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
        Logger.log("<Orientation.getOpposite(int i)");
        return direction;
    }

}
