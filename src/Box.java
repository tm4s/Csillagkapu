/**
 * Doboz
 */

public class Box extends Field {

    /**
     * hozza tartozo ezredes
     * es merleg akit ertesitenie kell ha felveszik rola
     */
    private Colonel owner = null;
    private Scale ownedScale = null;

    public Box(Colonel colonel) {
        owner = colonel;
    }

    public Colonel getOwner(){
        return owner;
    }

    public void setOwner(Colonel owner) {
        this.owner = owner;
    }

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
    public Character print() {
        return 'B';
    }

    @Override
    public  void collideWith(ColonelsHand hand) {
        if (!hand.hasBox())
            hand.getColonel().boxPickUp(this);
    }
}
