/**
 * Mérleg
 */

public class Scale extends Field {
    private Door door = new Door();

    public Scale(Door door) {
    	Logger.log(">Scale.Scale(Door door)");
        this.door = door;
        Logger.log("<Scale.Scale(Door door)");
    }

    @Override
    public void collideWith(Colonel colonel) {
    	Logger.log(">Scale.collideWith(Colonel colonel)");
        colonel.moveTo(this);
        Logger.log("<Scale.collideWith(Colonel colonel)");
    }

    @Override
    public void collideWith(Bullet bullet) {
    	Logger.log(">Scale.collideWith(Bullet bullet)");
        bullet.moveForward();
        Logger.log("<Scale.collideWith(Bullet bullet)");
    }

    @Override
    public void collideWith(ColonelsHand hand) {
    	Logger.log(">Scale.collideWith(ColonelsHand ColonelsHand)");
        if (hand.hasBox())
            hand.getColonel().boxPutDownToScale(this);
        Logger.log("<Scale.collideWith(ColonelsHand ColonelsHand)");
    }

    public void addWeight() {
        Logger.log(">Scale.addWeight()");
    	door.open();
    	Logger.log("<Scale.addWeight()");
    }

    public void removeWeight() {
        Logger.log(">Scale.removeWight()");
    	door.close();
    	Logger.log("<Scale.removeWight()");
    }

}
