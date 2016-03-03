/**
 * Doboz
 */

public class Box extends Field {
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
    public void collideWith(Colonel colonel) {
    }

    @Override
    public void collideWith(Bullet bullet) {

    }

    /**
     * ha a box amivel utkozott az ezredes tulajdona akkor meghivja az ezredes doboz felvevo fuggvenyet
     * (csak az ezredes tulajdona lehet maskent nem utkozhetne vele)
     * @param box ezzel utkozott
     */
    @Override
    public void collideWith(Box box) {
        if (box.getOwner() != null) {
            box.getOwner().boxPickUp(this);
        }
    }
}
