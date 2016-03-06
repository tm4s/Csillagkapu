/**
 * Csillagkapu
 */
public class Teleporter extends Field {
    /**
     * csillagkapuk tipusai
     */
    enum Type {
        ORANGE, BLUE
    }

    /**
     * palyan levo lerakott csillagkapuk
     * static mivel csak 2 lehet es mivel tudniuk kell egymasrol
     * palyan letrehozott csillagkapura tarol referenciat
     */
    private static Teleporter[] Teleporters = new Teleporter[2];


    private Type type;

    /**
     * konstruktor
     * ha ugyanolyan tipusu csillagkapu mar van a palyan akkor annak a helyen letrehoz egy specialis falat
     * (mivel a csillagkapu elott ennek kellett a helyen lennie)
     * majd magara allitja a statikus vele megegyezo csillagkapu referenciat
     * @param type csillagkapu tipusa (szine)
     */
    public Teleporter(Type type) {
        this.type = type;
        if (Teleporters[type.ordinal()] != null) {
            Teleporters[type.ordinal()].setField(new SpecialWall());
        }
        Teleporters[type.ordinal()] = this;
    }

    /**
     * masik teleporter lekrese
     * @return ellenkezo tipusu csillagkapu lehet null
     *  csak olvashatosag kedveert van
     */
    private Teleporter getOtherTeleporter() {
        return Teleporters[(type.ordinal()+1)%2];
    }

    @Override
    public void collideWith(Colonel colonel) {
        if (getOtherTeleporter() != null)
            colonel.teleportTo(getOtherTeleporter());

    }

    @Override
    public Character print() {
        Character c = '0';
        if (type == Type.ORANGE) c = 'O';
        return c;
    }
}
