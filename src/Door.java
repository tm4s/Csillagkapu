/**
 * Ajtó
 */

public class Door extends Field {
    private boolean isOpened = false;

    public void open() {
        isOpened = true;
    }

    public void close() {
        isOpened = false;
    }

    @Override
    public void collideWith(Colonel colonel) {
        if (isOpened) {
            colonel.moveTo(this);
        }

    }

    @Override
    public void collideWith(Bullet bullet) {
        if (isOpened) {
            bullet.moveForward();
        }

    }

    @Override
    public void collideWith(Box box) {

    }

    @Override
    public Character print() {
        Character c = 'D';
        if (isOpened) c = ' ';
        return c;
    }



}
