/**
 * Ezredes
 */

public class Colonel{
    private Coordinate position;

    public Colonel(Coordinate position) {
        position = new Coordinate(position);
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
