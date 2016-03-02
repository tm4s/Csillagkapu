/**
 * Irány tárolására alkalmas osztály
 *
 * Debugnál figyelni kell az irányokra!!!!
 */


public class Orientation {
    enum OrientationType {
        NORTH, WEST, SOUTH, EAST
    }

    private Coordinate coordinate;

    public Orientation(OrientationType type) {
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
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

}
