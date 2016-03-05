/**
 * Csillagkapu
 */
public class Teleporter extends Field {
    enum Type {
        ORANGE, BLUE
    }

    private static Teleporter[] Teleporters = new Teleporter[2];


    private Type type;

    public Teleporter(Type type) {
        this.type = type;
        if (Teleporters[type.ordinal()] != null) {
            Teleporters[type.ordinal()].setField(new SpecialWall());
        }
        Teleporters[type.ordinal()] = this;
    }

    private Teleporter getOtherTeleporter() {
        return Teleporters[(type.ordinal()+1)%2];
    }

    @Override
    public void collideWith(Colonel colonel) {
        if (getOtherTeleporter() != null)
            colonel.TeleportTo(getOtherTeleporter());

    }

    @Override
    public void collideWith(Bullet bullet) {

    }

    @Override
    public void collideWith(Box box) {

    }

    @Override
    public Character print() {
        Character c = '0';
        if (type == Type.ORANGE) c = 'O';
        return c;
    }
}
