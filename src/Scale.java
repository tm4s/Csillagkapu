/**
 * Mérleg
 */

public class Scale extends Field {
    private Door door = null;

    public Scale(Coordinate position) {
        super(position);
    }

    @Override
    public void collideWith(Colonel colonel) {
        colonel.moveTo(this);
    }

    @Override
    public void collideWith(Bullet bullet) {
    }

    public void addWeight() {
        door.open();
    }

    public void removeWeight() {
        door.close();
    }
}
}
