/**
 * Ezredes
 */

public class Colonel{

    private Field ownedField;
    private Orientation.Type orientation;

    private Scale ownedScale = null;
    private Box ownedBox = null;

    /**
     * konstruktor
     * @param field szüksége van egy kezdo mezore
     */
    public Colonel(Field field) {
        ownedField = field;
        orientation = Orientation.Type.NORTH;
    }

    /**
     * az ezredes a megadott iranyba fordul
     * @param direction a fordulas iranya
     */
    // meg csak abszolut fordul a megadott iranyba
    public void rotateTo(Orientation.Type direction) {
        orientation = direction;
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
        orientation = direction;
        ownedField.getNextField(orientation).collideWith(this);
    }

    /**
     * üres mezőre lépés
     * ha eddig mérlegen állt akkor értesíti a mérleget hogy lelépett és törli a rá mutató referenciáját
     */
    public void moveTo(Field field) {
        ownedField = field;
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
        ownedField = scale;
        if (ownedScale != null) {
            ownedScale.removeWeight();
            ownedScale = null;
        }
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
    public void tryBoxPickUp() {
        if (ownedBox == null) {
            ownedField.getNextField(orientation).collideWith(new Box(this));
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
            Scale boxScale = ownedBox.getOwnedScale();
            if (boxScale == null) {
                ownedField.getNextField(orientation).setField(new EmptyField());
            } else {
                ownedField.getNextField(orientation).setField(boxScale);
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
            ownedField.getNextField(orientation).collideWith(ownedBox);
        }
    }

    /**
     * doboz lerakasa ures mezore ha van nalunk
     * @param emptyField erre a mezore
     */
    public void boxPutDownToEmptyField(EmptyField emptyField) {
        if (ownedBox != null) {
            ownedField.getNextField(orientation).setField(ownedBox);
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
            ownedField.getNextField(orientation).setField(ownedBox);
            ownedBox = null;
        }
    }

    public void shootTeleporter(Teleporter.Type type) {
        Bullet bullet = new Bullet(type, ownedField, orientation);
        bullet.moveForward();
    }




    public void TeleportTo(Field field) {
        ownedField = field;
    }


    //csak teszteleshez
    public Field getOwnedField(){
        return ownedField;
    }
    public Orientation.Type getOrientation(){
        return orientation;
    }
}
