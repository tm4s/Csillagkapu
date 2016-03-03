/**
 * Üres mező
 */

public class EmptyField extends Field {

    public EmptyField(Coordinate position) {
        super(position);
    }

    @Override
    public void collideWith(Colonel colonel) {
        colonel.moveTo(this);
    }

    @Override
    public void collideWith(Bullet bullet) {

    }
}
