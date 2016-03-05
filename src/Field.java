/**
 * Ilyen mező típusokból áll a pálya
 */

public abstract class Field {
    // csak teszteleshez kell
    private Coordinate position = new Coordinate(-1, -1);
    private Map map = null;


    private Field[] nextFields = new Field[4];


    public void setNextField(Orientation.Type direction, Field field) {
        nextFields[direction.ordinal()] = field;
    }

    public void setField(Field field) {
        field.setNextFields(nextFields);

        // csak teszteleshez kell
        field.position = position;
        field.setOnMap();
    }

    public Field getNextField(Orientation.Type direction) {
        return nextFields[direction.ordinal()];
    }

    public Field[] getNextFields() {
        return nextFields;
    }

    public void setNextFields(Field[] nextFields) {
        for (int i = 0; i < nextFields.length; ++i) {
            this.nextFields[i] = nextFields[i];
            this.nextFields[i].setNextField(Orientation.getOpposite(i), this);
        }
    }

    public abstract void collideWith(Colonel colonel);
    public abstract void collideWith(Bullet bullet);
    public abstract void collideWith(Box box);

    public abstract Character print();

    //csak teszteleshez kell
    public Coordinate getPosition() {
        return position;
    }
    public void setPosition(Coordinate position) {
        this.position = new Coordinate(position);
    }
    public void setOnMap(){
        map.setFieldAt(position, this);
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
