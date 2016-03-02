/**
 * Doboz
 */

public class Box extends Field {
    private Colonel owner = null;

    public Box(Colonel colonel) {
        owner = colonel;
    }

    public Colonel getOwner(){
        return owner;
    }

    @Override
    public void collideWith(Colonel colonel) {
    }

    @Override
    public void collideWith(Bullet bullet) {

    }

    /**
     * ha a box amivel utkozott az ezredes tulajdona akkor meghivja az ezredes doboz felvevo fuggvenyet
     * @param box ezzel utkozott
     */
    @Override
    public void collideWith(Box box) {
        if (box.getOwner() != null) {
            box.getOwner().boxPickUp(this);
        }
    }
}
