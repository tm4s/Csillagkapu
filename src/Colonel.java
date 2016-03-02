/**
 * Ezredes
 */

public class Colonel{
    private Map map;

    private Coordinate position;
    private Coordinate orientation;

    private Scale ownedScale = null;
    private Box ownedBox = null;
    private boolean tryBoxPicking = false;

    /**
     * konstruktor
     * @param map szüksége van egy inicializált pályára
     */
    public Colonel(Map map) {
        this.map = map;
        position = new Coordinate(map.getColonelStartingPosition());
        orientation = Orientation.getCoordinate(Orientation.Type.NORTH);
    }

    /**
     * az ezredes utasítást kap hogy mozogjon,
     * saját pozíció + irány = mező koordinátája ahova lépni akarunk
     * ezt a mezőt le kell kérni a mapből és meg kell rá hívni az ütközés függvényt
     * (az ezredest magát átadva paraméternek)
     * @param direction ebbe az irányba mozogjon
     */
    public void goTo(Orientation.Type direction) {
        Coordinate destination = new Coordinate(position.add(Orientation.getCoordinate(direction)));
        map.getFieldAt(destination).collideWith(this);
    }

    /**
     * üres mezőre lépés
     * ha eddig mérlegen állt akkor értesíti a mérleget hogy lelépett és törli a rá mutató referenciáját
     * @param emptyField erre amezőre lép (ezzel ütközött)
     */
    public void moveTo(EmptyField emptyField) {
        position = new Coordinate(emptyField.getPosition());
        if (ownedScale != null) {
            ownedScale.removeWeight();
            ownedScale = null;
        }
    }

    /**
     * mérlegre való rálépés
     * értesíti a mérleget hogy súly került rá, eltárol rá egy referenciát (a lelépés miatt)
     * @param scale erre a mérlegre lép rá
     */
    public void moveTo(Scale scale) {
        position = new Coordinate((scale.getPosition()));
        scale.addWeight();
        this.ownedScale = scale;
    }

    public void pickUpBox(Box box) {
        if (tryBoxPicking) {
            this.ownedBox = box;
            tryBoxPicking = false;
        }
    }

    public boolean pickUpBox(Field field){
        if (this.ownedBox != null) {
            return false;
        }
        tryBoxPicking = true;
        field.collideWith(this);
        if (!tryBoxPicking) {
            return true;
        }
        tryBoxPicking = false;
        return false;

    }

    public Coordinate getPosition() {
        return position;
    }
    public  Coordinate getFrontFieldPosition() {
        return position.add(orientation);
    }
}
