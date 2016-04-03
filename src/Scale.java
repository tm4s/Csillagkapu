/**
 * Mérleg
 */

public class Scale extends Field {
    private Door door = new Door();

    public Scale(Door door) {
        this.door = door;
    }

    @Override
    public void collideWith(Colonel colonel) {
        colonel.moveTo(this);
    }

    public void addBox(Box box) {

    }

    public Box popBox() {

    }

    @Override
    public void collideWith(Bullet bullet) {
        bullet.moveForward();
    }

    @Override
    public void collideWith(ColonelsHand hand) {
        if (hand.hasBox())
            hand.getColonel().boxPutDownToScale(this);
    }

    public void addWeight(int weight) {
        door.open();
    }

    public void removeWeight(int weight) {
        door.close();
    }

}
