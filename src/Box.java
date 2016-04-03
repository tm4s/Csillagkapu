/**
 * Doboz
 */

public class Box extends Field {

    /**
     * es merleg akit ertesitenie kell ha felveszik rola
     */

    public Box() {
    }

    public int getWeight() {

    }

    @Override
    public void collideWith(Bullet bullet) {
        bullet.moveForward();
    }

    @Override
    public  void collideWith(ColonelsHand hand) {
        if (!hand.hasBox())
            hand.getColonel().boxPickUp(this);
    }
}