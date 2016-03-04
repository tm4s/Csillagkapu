/**
 * Ezredes
 */

public class Colonel{
    private Map map;

    private Coordinate position;
    private Coordinate orientation;

    private Scale ownedScale = null;
    private Box ownedBox = null;

    private int collectedZpms = 0;
    private boolean dead = false;

    /**
     * konstruktor
     * @param map szüksége van egy inicializált pályára
     */
    public Colonel(Map map) {
        this.map = map;
        position = new Coordinate(map.getColonelStartingPosition());
        orientation = Orientation.getCoordinate(Orientation.Type.NORTH);
    }

    public int getCollectedZpms() {
        return collectedZpms;
    }

    public boolean isDead() {
        return dead;
    }


    /**
     * az ezredes a megadott iranyba fordul
     * @param direction a fordulas iranya
     */
    // meg csak abszolut fordul a megadott iranyba
    public void rotateTo(Orientation.Type direction) {
        orientation = new Coordinate(Orientation.getCoordinate(direction));
    }

    /**
     * az ezredes utasítást kap hogy mozogjon,
     * saját pozíció + irány = mező koordinátája ahova lépni akarunk
     * ezt a mezőt le kell kérni a mapből és meg kell rá hívni az ütközés függvényt
     * (az ezredest magát átadva paraméternek)
     * @param direction ebbe az irányba mozogjon
     */
    // meg csak abszolut mozog orientaciot nem veszi figyelembe
    public void goTo(Orientation.Type direction) {
        orientation = new Coordinate(Orientation.getCoordinate(direction));
        map.getFieldAt(getFrontFieldPosition()).collideWith(this);
    }

    /**
     * üres mezőre lépés
     * ha eddig mérlegen állt akkor értesíti a mérleget hogy lelépett és törli a rá mutató referenciáját
     */
    public void moveTo() {
        position = new Coordinate(getFrontFieldPosition());
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
        position = new Coordinate(getFrontFieldPosition());
        scale.addWeight();
        this.ownedScale = scale;
    }

    /**
     * ZPM modulok felvétele
     * Amikor az ezredes rálépne egy zpm modulra akkor a modul mező üres mezővé alakul,
     * eközben az ezredes is rálép, valamint az eddig összegyűjtött zpmek számát megnöveljük eggyel.
     * @param zpm ezt a zpm modult veszi fel
     */

    public void moveTo(Zpm zpm) {
        Coordinate destination = new Coordinate(getFrontFieldPosition());
        map.setFieldAt(destination, new EmptyField());
        position = new Coordinate(getFrontFieldPosition());
        this.collectedZpms++;
    }

    /**
     * Ha az ezredes szakadékba lép meghal.
     * 
     * @param ravine ebbe a szakadekba lep bele
     */
    public void moveTo(Ravine ravine) {
        position = new Coordinate(getFrontFieldPosition());
        this.dead = true;
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
                map.setFieldAt(destination, new EmptyField());
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
            map.setFieldAt(getFrontFieldPosition(), ownedBox);
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

    public void shootTeleporter(Teleporter.Type type) {
        Bullet bullet = new Bullet(type, position, orientation, map);
        bullet.moveForward();
    }


    public Coordinate getPosition() {
        return position;
    }

    /**
     * @return ezredes elotti mezo koordniataja
     */
    private Coordinate getFrontFieldPosition() {
        return position.add(orientation);
    }

    public void TeleportTo(Coordinate position) {
        this.position = position;
    }

    public Coordinate getOrientation() {
        return orientation;
    }
}
