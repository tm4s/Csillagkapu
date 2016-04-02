/**
 * Ezredes
 */

public class Colonel{

    /**
     * mezo amin all az ezredes
     * irany amibe nez
     * merleg amin all
     * doboz ami nala van
     * begyujtott ZPM modulok szama
     * meghalt-e az ezredes szakadektol
     */
    private Field ownedField;
    private Orientation.Type orientation;
    private Scale ownedScale = null;
    private Box ownedBox = null;
    private ColonelsHand hand;
    private int collectedZpms = 0;
    private boolean dead = false;       //lehet kesobb nem fog kelleni



    /**
     * konstruktor
     * @param field szuksege van egy mezore amin all majd
     */
    public Colonel(Field field) {
        hand = new ColonelsHand(this);
        ownedField = field;
        orientation = Orientation.Type.NORTH;
    }

    /**
     * elkeri a mezotol amin az ezredes all azt a mezot ami abba az iranyba van tole amibe az ezreds nez
     * @return megfelelo mezot
     * csak a kod olvashatosaga miatt lett letrehozva
     */
    private Field getFrontField() {
        return ownedField.getNextField(orientation);
    }

    //valamit meg ezzel lehet kell majd csinalni
    public int getCollectedZpms() {
        return collectedZpms;
    }

    //valamit meg ezzel lehet kell majd csinalni
    public boolean isDead() {
        return dead;
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
     * az ezredes uatsitast kap hogy mozogjon,
     * mostani mezorol milyen iranyba probaljon lepni egy masik mezore
     * attol a mezotol amin all ell kell kerni a tole megadott iranyban levo szomszedjat
     * es meg kell ra hivni az utkozes fuggvenyt (az ezredest magát átadva paraméternek)
     * @param direction ebbe az iranyba mozogjon
     */
    // meg csak abszolut mozog orientaciot nem veszi figyelembe
    public void tryMoveTo(Orientation.Type direction) {
        orientation = direction;
        getFrontField().collideWith(this);
    }

    /**
     * ures mezore lepes
     * ha eddig merlegen allt akkor ertesiti a merleget hogy lelepett és torli a ra mutato referenciajat
     */
    public void moveTo(Field field) {
        ownedField = field;
        notifyOwnedScale();
    }

    /**
     * mérlegre való rálépés
     * értesíti a mérleget hogy súly került rá, eltárol rá egy referenciát (a lelépés miatt)
     * @param scale erre a mérlegre lép rá
     */
    public void moveTo(Scale scale) {
        ownedField = scale;
        notifyOwnedScale();
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
        ownedField = new EmptyField();
        notifyOwnedScale();
        zpm.setField(ownedField);

        this.collectedZpms++;

        if ((collectedZpms % 2) == 0) {
            zpm.setNewPosition(ownedField);
        }
    }

    /**
     * Ha az ezredes szakadékba lép meghal.
     *
     * @param ravine ebbe a szakadekba lep bele
     */
    public void moveTo(Ravine ravine) {
        ownedField = ravine;
        notifyOwnedScale();
        this.dead = true;
    }

    /**
     * ha merlegen all az ezredes ertesiti azt hogy lelepett rola
     */
    private void notifyOwnedScale() {
        if (ownedScale != null) {
            ownedScale.removeWeight();
            ownedScale = null;
        }
    }

    /**
     * doboz felvételére kísérlet az ezredes elotti mezorol
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
            getFrontField().collideWith(hand);
        }
    }

    /**
     * doboz felvetele az ezredes elotti mezorol
     * ha nincs nalunk doboz
     * a helyen letrehoz egy ures mezot ha nem tartozott hozza lenyomott merleg
     * ha tartozott akkor felengedjuk amerleget es azt tesszuk a helyere
     * @param box ezt a dobozt vesszük fel
     */
    public void boxPickUp(Box box) {
        ownedBox = box;
        hand.setHasBox(true);
        Scale boxScale = ownedBox.getOwnedScale();
        if (boxScale == null) {
            box.setField(new EmptyField());
        } else {
            box.setField(boxScale);
            boxScale.removeWeight();
            ownedBox.setOwnedScale(null);
        }
    }

    /**
     * doboz lerakasara kiserlet
     */
    public void tryBoxPutDown() {
        if (ownedBox != null) {
            getFrontField().collideWith(hand);
        }
    }

    /**
     * doboz lerakasa ures mezore ha van nalunk
     * @param emptyField erre a mezore
     */
    public void boxPutDownToEmptyField(EmptyField emptyField) {
        emptyField.setField(ownedBox);
        ownedBox = null;
        hand.setHasBox(false);
    }

    /**
     * doboz lerakasa merlegre ha van nalunk
     * merleg ertesitese a lenyomasrol
     * dobozban eltarolunk ra egy referenciat a doboz felvetelnel valo ertesites miatt
     * @param scale erre a merlegre
     */
    public void  boxPutDownToScale(Scale scale) {
        ownedBox.setOwnedScale(scale);
        scale.addWeight();
        scale.setField(ownedBox);
        ownedBox = null;
        hand.setHasBox(false);
    }


    /**
     * Doboz lerakása szakadékba
     * Ennek hatására az ezredesnél lévő doboz megszűnik.
     * @param ravine ebbe a szakadekba tesszuk (nem kell igazabol)
     */
    public void boxPutDownToRavine(Ravine ravine) {
        if (ownedBox != null) {
            ownedBox = null;
            hand.setHasBox(false);
        }
    }

    /**
     * Teleporter kilovese (csak akkor lesz a kilott lovedekbol teleporter ha sepcialis falnak utkozik
     * a kilott lovedeknek megadjuk a mezot amin allunk es az iranyt
     * valamint elinditjuk a lovedeket (ezutan addig fog haladni ameddig bele nem utkozik
     * valamilyen objektumba ami nem engedi tovabb vagy specialis falba ahol letrehoz egy teleportert
     * @param type ilyen tipusu (szinu) teleportert akarunk letrehozni
     */
    public void shootTeleporter(Teleporter.Type type) {
        Bullet bullet = new Bullet(type, ownedField, orientation);
        bullet.moveForward();
    }

    /**
     * teleportalas
     * ezredes athelyezese a megadott mezore
     * @param teleporter erre a mezore teleportalunk
     */
    public void teleportTo(Teleporter teleporter) {
        ownedField = teleporter;
        tryMoveTo(teleporter.getOrientation());
    }


    //csak teszteleshez
    public Field getOwnedField(){
        return ownedField;
    }
    public Orientation.Type getOrientation(){
        return orientation;
    }

}
