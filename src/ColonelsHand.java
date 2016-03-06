/**
 * Ezredes keze a doboz felvetelhez
 */

public class ColonelsHand {
    private Colonel colonel;
    private boolean hasBox;

    public ColonelsHand(Colonel colonel) {
        this.colonel = colonel;
    }

    public Colonel getColonel() {
        return colonel;
    }

    public boolean hasBox() {
        return hasBox;
    }

    public void setHasBox(boolean hasBox) {
        this.hasBox = hasBox;
    }
}
