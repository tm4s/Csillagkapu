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
     * ha mezőn doboz van akkor az visszahivja a mi boxPickUp fuggvenyunket magaval
     * ha nem doboz van a mezon akkor az erzekeli hogy egy hozzank tartozo dobozzal utkozott
     * visszahivja a doboz lerakasa funkcionkat ezert ebben ellenorzini kell hogy van-e nalunk doboz es ha nincs
     * akkor tudjuk hogy innen tortent a visszahivas
     */
    // kicsit necces de talan jo
    public void tryBoxPicUp() {
        if (ownedBox == null) {
            map.getFieldAt(getFrontFieldPosition()).collideWith(new Box(this));
        }
    }

    /**
     * doboz felvetele
     * ha nincs nalunk doboz
     * a helyen letrehoz egy ures mezot ha nem tartozott hozza lenyomott merleg
     * ha tartozott akkor felengedjuk amerleget es azt tesszuk a helyere
     * @param box ezt a dobozt vesszük fel
     */
    public void boxPickUp(Box box) {
        if (ownedBox == null) {
            ownedBox = box;
            ownedBox.setOwner(this);
            Coordinate destination = new Coordinate(getFrontFieldPosition());
            Scale boxScale = ownedBox.getOwnedScale();
            if (boxScale == null) {
                map.setFieldAt(destination, new EmptyField(destination));
            } else {
                map.setFieldAt(destination, boxScale);
                boxScale.removeWeight();
                ownedBox.setOwnedScale(null);
            }
        }
    }

    /**
     * doboz lerakasara kiserlet
     */
    public void tryBoxPutDown() {
        if (ownedBox != null) {
            map.getFieldAt(getFrontFieldPosition()).collideWith(ownedBox);
        }
    }

    /**
     * doboz lerakasa ures mezore ha van nalunk
     * @param emptyField erre a mezore
     */
    public void boxPutDownToEmptyField(EmptyField emptyField) {
        if (ownedBox != null) {
            map.setFieldAt(emptyField.getPosition(), ownedBox);
            ownedBox = null;
        }
    }

    /**
     * doboz lerakasa merlegre ha van nalunk
     * merleg ertesitese a lenyomasrol
     * dobozban eltarolunk ra egy referenciat a doboz felvetelnel valo ertesites miatt
     * @param scale erre a merlegre
     */
    public void  boxPutDownToScale(Scale scale) {
        if (ownedBox != null) {
            ownedBox.setOwnedScale(scale);
            scale.addWeight();
            map.setFieldAt(getFrontFieldPosition(), ownedBox);
            ownedBox = null;
        }
    }


    public Coordinate getPosition() {
        return position;
    }

    /**
     * @return ezredes elotti mezo koordniataja
     */
    private   Coordinate getFrontFieldPosition() {
        return position.add(orientation);
    }
}
