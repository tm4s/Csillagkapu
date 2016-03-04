/**
 * Ilyen mező típusokból áll a pálya
 */

public abstract class Field {
    private Field[] nextFields = new Field[4];

    public void setNextField(Orientation.Type direction, Field field) {
        nextFields[direction.ordinal()] = field;
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
}
