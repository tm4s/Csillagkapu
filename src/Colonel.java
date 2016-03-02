/**
 * Ezredes
 */

public class Colonel{
    enum OrientationType {
        NORTH, WEST, SOUTH, EAST
    }

    private Coordinate position;
    private Coordinate orientation;



    public Colonel(Coordinate position) {
        position = new Coordinate(position);
        orientation = Orientation.getCoordinate(Orientation.Type.NORTH);
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
