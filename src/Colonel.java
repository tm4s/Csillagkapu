/**
 * Ezredes
 */

public class Colonel{
    enum OrientationType {
        NORTH, WEST, SOUTH, EAST
    }

    private Coordinate position;
    private Coordinate orientation;

    public void setOrientation(OrientationType type) {
        switch (type){
            case NORTH:
                orientation = new Coordinate(0,1);
                break;
            case WEST:
                orientation = new Coordinate(-1,0);
                break;
            case SOUTH:
                orientation = new Coordinate(0,-1);
                break;
            case EAST:
                orientation = new Coordinate(1,0);
                break;

        }
    }

    public Colonel(Coordinate position) {
        position = new Coordinate(position);
        orientation = new Coordinate(0,1);      //NORTH
    }

    public void collideWith(Field field) {
        field.accept(this);
    }

    public void collideWith(EmptyField emptyField) {
        setPosition(emptyField.getPosition());
    }

    public void setPosition(Coordinate position) {
        this.position = new Coordinate(position);
    }

    public Coordinate getPosition() {
        return position;
    }
}
