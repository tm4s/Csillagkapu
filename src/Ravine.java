/**
 * Szakadék
 */
public class Ravine extends Field {

    public Ravine() {}

    @Override
    public void collideWith(Colonel colonel) {
        colonel.moveTo(this);
    }

    @Override
    public void collideWith(Bullet bullet) {
        bullet.moveForward();
    }

    @Override
    public void collideWith(ColonelsHand hand) {
        if (hand.hasBox())
            hand.getColonel().boxPutDownToRavine(this);
    }

    @Override
    public void setReplicator(Replicator replicator) {
        setField(new EmptyField());
        replicator.die();
    }

}
