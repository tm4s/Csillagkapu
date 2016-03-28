/**
 * Ezredes
 */

public class Colonel {

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
	 *
	 * @param field szuksege van egy mezore amin all majd
	 */
	public Colonel(Field field) {
		Logger.log(">Colonel.Colonel(Field field)");
		hand = new ColonelsHand(this);
		ownedField = field;
		orientation = Orientation.Type.NORTH;
		Logger.log("<Colonel.Colonel(Field field)");
	}

	/**
	 * elkeri a mezotol amin az ezredes all azt a mezot ami abba az iranyba van tole amibe az ezreds nez
	 *
	 * @return megfelelo mezot
	 * csak a kod olvashatosaga miatt lett letrehozva
	 */
	private Field getFrontField() {
		Logger.log(">Colonel.getFrontField()");
		Field field = ownedField.getNextField(orientation);
		Logger.log("<Colonel.getFrontField()");
		return field;
	}

	//valamit meg ezzel lehet kell majd csinalni
	public int getCollectedZpms() {
		Logger.log(">Colonel.getCollectedZpms()");
		Logger.log("<Colonel.getCollectedZpms()");
		return collectedZpms;
	}

	//valamit meg ezzel lehet kell majd csinalni
	public boolean isDead() {
		Logger.log(">Colonel.isDead()");
		Logger.log("<Colonel.isDead()");
		return dead;
	}


	/**
	 * az ezredes a megadott iranyba fordul
	 *
	 * @param direction a fordulas iranya
	 */
	// meg csak abszolut fordul a megadott iranyba
	public void rotateTo(Orientation.Type direction) {
		Logger.log(">Colonel.rotateTo(Orientation.Type direction)");
		orientation = direction;
		Logger.log("<Colonel.rotateTo(Orientation.Type direction)");
		
	}

	/**
	 * az ezredes uatsitast kap hogy mozogjon,
	 * mostani mezorol milyen iranyba probaljon lepni egy masik mezore
	 * attol a mezotol amin all ell kell kerni a tole megadott iranyban levo szomszedjat
	 * es meg kell ra hivni az utkozes fuggvenyt (az ezredest magat atadva parameternek)
	 *
	 * @param direction ebbe az iranyba mozogjon
	 */
	// meg csak abszolut mozog orientaciot nem veszi figyelembe
	public void tryMoveTo(Orientation.Type direction) {
		Logger.log(">Colonel.tryMoveTo(Orientation.Type direction)");
		orientation = direction;
		getFrontField().collideWith(this);
		Logger.log("<Colonel.tryMoveTo(Orientation.Type direction)");
	}

	/**
	 * ures mezore lepes
	 * ha eddig merlegen allt akkor ertesiti a merleget hogy lelepett es torli a ra mutato referenciajat
	 */
	public void moveTo(Field field) {
		Logger.log(">Colonel.moveTo(Field field)");
		ownedField = field;
		if (ownedScale != null)
			notifyOwnedScale();
		Logger.log("<Colonel.moveTo(Field field)");
	}

	/**
	 * merlegre valo ralepes
	 * Ertesiti a merleget hogy suly kerult ra, eltarol egy referenciat (a lelepes miatt)
	 *
	 * @param scale erre a merlegre lep ra
	 */
	public void moveTo(Scale scale) {
		Logger.log(">Colonel.moveTo(Scale scale)");
		ownedField = scale;
		if (ownedScale != null)
			notifyOwnedScale();
		scale.addWeight();
		this.ownedScale = scale;
		Logger.log("<Colonel.moveTo(Scale scale)");
	}

	/**
	 * ZPM modulok felvetele
	 * Amikor az ezredes ralepne egy ZPM modulra, akkor a modul mezo ures mezove alakul,
	 * ekozben az ezredes is ralep, valamint az eddig osszegyujtott ZPM-ek szamat is megnoveljuk eggyel.
	 *
	 * @param zpm ezt a zpm modult veszi fel
	 */

	public void moveTo(Zpm zpm) {
		Logger.log(">Colonel.moveTo(Zpm zpm)");
		ownedField = new EmptyField();
		if (ownedScale != null)
			notifyOwnedScale();
		zpm.setField(ownedField);
		this.collectedZpms++;
		Logger.log("<Colonel.moveTo(Zpm zpm)");
	}

	/**
	 * Ha az ezredes szakadekba lep meghal.
	 *
	 * @param ravine ebbe a szakadekba lep bele
	 */
	public void moveTo(Ravine ravine) {
		Logger.log(">Colonel.moveTo(Ravine ravine)");
		ownedField = ravine;
		if (ownedScale != null)
			notifyOwnedScale();
		this.dead = true;
		Logger.log("<Colonel.moveTo(Ravine ravine)");
	}

	/**
	 * ha merlegen all az ezredes ertesiti azt hogy lelepett rola
	 */
	private void notifyOwnedScale() {
		Logger.log(">Colonel.notifyOwnedScale()");
		if (ownedScale != null) {
			ownedScale.removeWeight();
			ownedScale = null;
		}
		Logger.log("<Colonel.notifyOwnedScale()");
	}

	/**
	 * doboz felvetelere kiserlet az ezredes elotti mezorol
	 * letrehozunk egy dobozt aminek a tulajdonosat beallitjuk magunkra es nem hagyjuk meg a default null-t
	 * (doboznak van egy parametere ami Colonel referencia)
	 * es ezt a dobozt Ã¼tkÃ¶ztetjÃ¼k a mezovel ami elottÃ¼nk van
	 * ha mezon doboz van akkor az visszahivja a mi boxPickUp fuggvenyunket magaval
	 * ha nem doboz van a mezon akkor az erzekeli hogy egy hozzank tartozo dobozzal utkozott
	 * visszahivja a doboz lerakasa funkcionkat ezert ebben ellenorzini kell hogy van-e nalunk doboz es ha nincs
	 * akkor tudjuk hogy innen tortent a visszahivas
	 */
	// kicsit necces de talan jo
	public void tryBoxPickUp() {
		Logger.log(">Colonel.tryBoxPickUp()");
		if (ownedBox == null) {
			getFrontField().collideWith(hand);
		}
		Logger.log("<Colonel.tryBoxPickUp()");
	}

	/**
	 * doboz felvetele az ezredes elotti mezorol
	 * ha nincs nalunk doboz
	 * a helyen letrehoz egy ures mezot ha nem tartozott hozza lenyomott merleg
	 * ha tartozott akkor felengedjuk amerleget es azt tesszuk a helyere
	 *
	 * @param box ezt a dobozt vesszÃ¼k fel
	 */
	public void boxPickUp(Box box) {
		Logger.log(">Colonel.boxPickUp(Box box)");
		ownedBox = box;
		hand.setHasBox(true);
		Scale boxScale = ownedBox.getOwnedScale();
		if (boxScale == null) {
			getFrontField().setField(new EmptyField());
		} else {
			getFrontField().setField(boxScale);
			boxScale.removeWeight();
			ownedBox.setOwnedScale(null);
		}
		Logger.log("<Colonel.boxPickUp(Box box)");
	}

	/**
	 * doboz lerakasara kiserlet
	 */
	public void tryBoxPutDown() {
		Logger.log(">Colonel.tryBoxPutDown()");
		if (ownedBox != null) {
			getFrontField().collideWith(hand);
		}
		Logger.log("<Colonel.tryBoxPutDown()");
	}

	/**
	 * doboz lerakasa ures mezore ha van nalunk
	 *
	 * @param emptyField erre a mezore
	 */
	public void boxPutDownToEmptyField(EmptyField emptyField) {
		Logger.log(">Colonel.boxPutDownToEmptyField(EmptyField emptyField)");
		getFrontField().setField(ownedBox);
		ownedBox = null;
		hand.setHasBox(false);
		Logger.log("<Colonel.boxPutDownToEmptyField(EmptyField emptyField)");
	}

	/**
	 * doboz lerakasa merlegre ha van nalunk
	 * merleg ertesitese a lenyomasrol
	 * dobozban eltarolunk ra egy referenciat a doboz felvetelnel valo ertesites miatt
	 *
	 * @param scale erre a merlegre
	 */
	public void boxPutDownToScale(Scale scale) {
		Logger.log(">Colonel.boxPutDownToScale(Scale scale)");
		ownedBox.setOwnedScale(scale);
		scale.addWeight();
		getFrontField().setField(ownedBox);
		ownedBox = null;
		hand.setHasBox(false);
		Logger.log("<Colonel.boxPutDownToScale(Scale scale)");
	}


	/**
	 * Doboz lerakasa szakadekba
	 * Ennek hatasara az ezredesnel levo doboz megszunik.
	 *
	 * @param ravine ebbe a szakadekba tesszuk (nem kell igazabol)
	 */
	public void boxPutDownToRavine(Ravine ravine) {
		Logger.log(">Colonel.boxPutDownToRavine(Ravine ravine)");
		if (ownedBox != null) {
			ownedBox = null;
			hand.setHasBox(false);
		}
		Logger.log("<Colonel.boxPutDownToRavine(Ravine ravine)");
	}

	/**
	 * Teleporter kilovese (csak akkor lesz a kilott lovedekbol teleporter ha sepcialis falnak utkozik
	 * a kilott lovedeknek megadjuk a mezot amin allunk es az iranyt
	 * valamint elinditjuk a lovedeket (ezutan addig fog haladni ameddig bele nem utkozik
	 * valamilyen objektumba ami nem engedi tovabb vagy specialis falba ahol letrehoz egy teleportert
	 *
	 * @param type ilyen tipusu (szinu) teleportert akarunk letrehozni
	 */
	public void shootTeleporter(Teleporter.Type type) {
		Logger.log(">Colonel.shootTeleporter(Teleporter.Type type)");
		Bullet bullet = new Bullet(type, ownedField, orientation);
		bullet.moveForward();
		Logger.log("<Colonel.shootTeleporter(Teleporter.Type type)");
	}

	/**
	 * teleportalas
	 * ezredes athelyezese a megadott mezore
	 *
	 * @param teleporter erre a mezore teleportalunk
	 */
	public void teleportTo(Teleporter teleporter) {
		Logger.log(">Colonel.teleportTo(Teleporter teleporter)");
		ownedField = teleporter;
		tryMoveTo(teleporter.getOrientation());
		Logger.log("<Colonel.teleportTo(Teleporter teleporter)");
	}


	//csak teszteleshez
	public Field getOwnedField() {
		Logger.log(">getOwnedField()");
		Logger.log("<getOwnedField()");
		return ownedField;
	}

	public Orientation.Type getOrientation() {
		Logger.log(">getOrientation()");
		Logger.log("<getOrientation()");
		return orientation;
	}
}