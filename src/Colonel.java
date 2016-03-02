/**
 * Ezredes
 */

public class Colonel{

    private Coordinate position;
    private Coordinate orientation;



    public Colonel(Coordinate position) {
        position = new Coordinate(position);
        orientation = Orientation.getCoordinate(Orientation.Type.NORTH);
    }

    public void collideWith(Field field) {
        field.collideWith(this);
    }

    public void moveTo(EmptyField emptyField) {
        position = new Coordinate(emptyField.getPosition());
    }

    public Coordinate getPosition() {
        return position;
    }
}
