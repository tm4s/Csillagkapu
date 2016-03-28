/**
 * Mérleg
 */

public class Scale extends Field {
	private Door door = new Door();

	/**
	 * Konstruktor, a paramterkent atadott ajto objektumot
	 * beallitja az adott merleghez tartozo ajtokent
	 *
	 * @param door adott merleghez tartozo ajto
	 */
	public Scale(Door door) {
		Logger.log(">Scale.Scale(Door door)");
		this.door = door;
		Logger.log("<Scale.Scale(Door door)");
	}

	/**
	 * Az ezredes merleggel valo talalkozasakor az ezredes a merlegre lep
	 *
	 * @param colonel
	 */
	@Override
	public void collideWith(Colonel colonel) {
		Logger.log(">Scale.collideWith(Colonel colonel)");
		colonel.moveTo(this);
		Logger.log("<Scale.collideWith(Colonel colonel)");
	}

	/**
	 * A lovedek merleggel valo talalkozasakor athalad a merleg felett
	 */
	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">Scale.collideWith(Bullet bullet)");
		bullet.moveForward();
		Logger.log("<Scale.collideWith(Bullet bullet)");
	}

	/**
	 * Amennyiben az ezredes kezeben doboz van az rakerul a merlegre
	 *
	 * @param hand
	 */
	@Override
	public void collideWith(ColonelsHand hand) {
		Logger.log(">Scale.collideWith(ColonelsHand ColonelsHand)");
		if (hand.hasBox())
			hand.getColonel().boxPutDownToScale(this);
		Logger.log("<Scale.collideWith(ColonelsHand ColonelsHand)");
	}

	/**
	 * A merleg lenyomodasaval a megfelelo ajto kinyilik
	 */
	public void addWeight() {
		Logger.log(">Scale.addWeight()");
		door.open();
		Logger.log("<Scale.addWeight()");
	}

	/**
	 * A terhelest leveve a merlegrol a megfelelo ajto becsukodik
	 */
	public void removeWeight() {
		Logger.log(">Scale.removeWight()");
		door.close();
		Logger.log("<Scale.removeWight()");
	}

}
