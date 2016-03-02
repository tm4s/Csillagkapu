/**
 * Üres mező
 */

public class EmptyField extends Field {

    public EmptyField(Coordinate position) {
        super(position);
    }

    @Override
    public void accept(Colonel colonel) {
        colonel.collideWith(this);
    }
}
