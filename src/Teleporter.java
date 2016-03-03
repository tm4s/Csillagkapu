/**
 * Csillagkapu
 */
public class Teleporter extends Field {
    enum Type {
        ORANGE, BLUE
    }
    private Type type;
    private Teleporter otherTeleporter;
    private Coordinate position;

    public Teleporter(Type type, Teleporter otherTeleporter, Coordinate position) {
        this.type = type;
        this.otherTeleporter = otherTeleporter;
        this.position = position;
    }

    public void setOtherTeleporter(Teleporter otherTeleporter) {
        this.otherTeleporter = otherTeleporter;
    }

    public Coordinate getPosition() {
        return position;
    }


    @Override
    public void collideWith(Colonel colonel) {
        if (otherTeleporter != null)
            colonel.TeleportTo(otherTeleporter.getPosition());

    }

    @Override
    public void collideWith(Bullet bullet) {

    }

    @Override
    public void collideWith(Box box) {

    }

    @Override
    public Character print() {
        return null;
    }
}
