/**
 * Doboz
 */

public class Box extends Field {

    /**
     * es merleg akit ertesitenie kell ha felveszik rola
     */
    private Scale ownedScale = null;

    public void setOwnedScale(Scale ownedScale) {
        this.ownedScale = ownedScale;
    }

    public Scale getOwnedScale() {
        return ownedScale;
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