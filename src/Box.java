/**
 * Doboz
 */

public class Box extends Field {

    private int boxesWeight;

    public Box(int weight) {
        boxesWeight = weight;
    }


    public int getWeight() {
        return boxesWeight;
    }

    /**
     * A lovedek talalkzoasa a dobozzal
     *
     * @param bullet
     */
    @Override
    public void collideWith(Bullet bullet) {
        bullet.moveForward();
    }

    /**
     * Az ezeredes kezenek a dobozzal valo talalkozasa
     *
     * @param hand
     */
    @Override
    public  void collideWith(ColonelsHand hand) {
        if (!hand.hasBox())
            hand.getColonel().boxPickUp(this);
    }

    @Override
    public void view(Controller controller) {
        controller.showView(this);
    }
}
