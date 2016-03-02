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
        Coordinate destination = new Coordinate(position.add(Orientation.getCoordinate(direction)));        // kezelni kell meg hogy elore menes eseten ne eszaknak menjen hanem az orientationnek megfeleloen
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

    /**
     * doboz felvételére kísérlet,
     * létrehozunk egy dobozt aminek a tulajdonosát beállítjuk magunkra és nem hagyjuk meg a default null-t
     * (doboznak van egy parametere ami Colonel referencia)
     * és ezt a dobozt ütköztetjük a mezővel ami előttünk van
     * ha mezőn ha doboz van akkor az visszahivja a mi boxPickUp fuggvenyunket magaval
     * ha nem doboz van a mezon akkor az erzekeli hogy egy hozzank tartozo dobozzal
     * (ezt nem akarjuk ratenni) utkozott es nem csinal semmit
     */
    // kicsit necces lehet ken meg jobb megoldas
    public void tryBoxPicUp() {
        if (ownedBox == null) {
            Coordinate destiantion = new Coordinate(position.add(orientation));
            map.getFieldAt(destiantion).collideWith(new Box(this));
        }
    }

    /**
     * doboz felvetele
     * @param box ezt a dobozt vesszük fel
     */
    public void boxPickUp(Box box) {
        ownedBox = box;
        Coordinate destiantion = new Coordinate(position.add(orientation));
        map.setFieldAt(destiantion, new EmptyField(destiantion));
    }



    public Coordinate getPosition() {
        return position;
    }
    public  Coordinate getFrontFieldPosition() {
        return position.add(orientation);
    }
}
