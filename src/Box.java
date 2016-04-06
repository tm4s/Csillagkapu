/**
 * Doboz
 */

public class Box extends Field {

    private int boxesWeight;


    public int getWeight() {
        return boxesWeight;
    }

    @Override
    public void collideWith(Bullet bullet) {
        bullet.moveForward();
    }

    @Override
    public Character print() {
        return 'B';
    }

    @Override
    public  void collideWith(ColonelsHand hand) {
        if (!hand.hasBox())
            hand.getColonel().boxPickUp(this);
    }
}
