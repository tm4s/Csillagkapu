/**
 * Ilyen mező típusokból áll a pálya
 */

public abstract class Field {
    private Coordinate position;

    public Field(Coordinate position) {
        this.position = new Coordinate(position);
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public abstract void accept(Colonel colonel);

}
