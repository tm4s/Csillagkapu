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
                coordinate = new Coordinate(-1, 0);
                break;
            case EAST:
                coordinate = new Coordinate(0, 1);
                break;

        }
        return coordinate;
    }

}
