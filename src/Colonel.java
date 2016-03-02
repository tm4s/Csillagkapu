/**
 * Ezredes
 */

public class Colonel{

    private Coordinate position;
    private Coordinate orientation;

    private Scale scale = null;



    public Colonel(Coordinate position) {
        position = new Coordinate(position);
        orientation = Orientation.getCoordinate(Orientation.Type.NORTH);
    }

    public void collideWith(Field field) {
        field.collideWith(this);
    }

    public void moveTo(EmptyField emptyField) {
        position = new Coordinate(emptyField.getPosition());
        if (scale != null) {
            scale.removeWeight();
            scale = null;
        }
    }

    public void moveTo(Scale scale) {
        position = new Coordinate((scale.getPosition()));
        scale.addWeight();
        this.scale = scale;
    }

    public Coordinate getPosition() {
        return position;
    }
}
