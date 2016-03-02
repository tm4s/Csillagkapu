/**
 * Doboz
 */

public class Box extends Field {
    public Box(Coordinate position) {
        super(position);
    }

    @Override
    public void collideWith(Colonel colonel) {
        colonel.pickUpBox(this);
    }

    @Override
    public void collideWith(Bullet bullet) {

    }
}
