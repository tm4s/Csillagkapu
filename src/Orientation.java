/**
 * Irány tárolására alkalmas osztály
 *
 * Debugnál figyelni kell az irányokra!!!!
 */




public class Orientation {
    public enum Type {
        NORTH, WEST, SOUTH, EAST
    }

    public static Coordinate getCoordinate (Orientation.Type type) {
        Coordinate coordinate = new Coordinate(0,0);
        switch (type){
            case NORTH:
                coordinate = new Coordinate(0,1);
                break;
            case WEST:
                coordinate = new Coordinate(-1,0);
                break;
            case SOUTH:
                coordinate = new Coordinate(0,-1);
                break;
            case EAST:
                coordinate = new Coordinate(1,0);
                break;

        }
        return coordinate;
    }

}
