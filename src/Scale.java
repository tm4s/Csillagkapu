/**
 * Mérleg
 */

public class Scale extends Field {
    private Door door = new Door();


    @Override
    public void collideWith(Colonel colonel) {
        colonel.moveTo(this);
    }

    @Override
    public void collideWith(Bullet bullet) {
    }

    @Override
    public void collideWith(Box box) {
        box.getOwner().boxPutDownToScale(this);
    }

    @Override
    public Character print() {
        return 'S';
    }

    public void addWeight() {
        door.open();
    }

    public void removeWeight() {
        door.close();
    }

}
