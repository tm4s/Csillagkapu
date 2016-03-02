/**
 * Irány tárolására alkalmas osztály
 *
 * Debugnál figyelni kell az irányokra!!!!
 */


public class Orientation {
    enum Type {
        NORTH, WEST, SOUTH, EAST
    }

    public static Coordinate getCoordinate (Type type) {
        switch (type){
            case NORTH:
                return new Coordinate(0,1);
                break;
            case WEST:
                return new Coordinate(-1,0);
                break;
            case SOUTH:
                return new Coordinate(0,-1);
                break;
            case EAST:
                return new Coordinate(1,0);
                break;

        }
    }

}
