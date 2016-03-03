/**
 * Üres mező
 */

public class EmptyField extends Field {

    @Override
    public void collideWith(Colonel colonel) {
        colonel.moveTo(this);
    }

    @Override
    public void collideWith(Bullet bullet) {

    }

    @Override
    public void collideWith(Box box) {
        box.getOwner().boxPutDownToEmptyField(this);
    }

    @Override
    public Character print() {
        return ' ';
    }
}
