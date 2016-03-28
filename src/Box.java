/**
 * Doboz
 */

public class Box extends Field {

	/**
	 * Merleg, amit ertesitenie kell ha felveszik rola
	 */
	private Scale ownedScale = null;

	/**
	 * Beallitja, hogy a doboz melyik merleghez tartozzon
	 *
	 * @param ownedScale
	 */
	public void setOwnedScale(Scale ownedScale) {
		Logger.log(">Box.setOwnedScale(Scale ownedScale)");
		this.ownedScale = ownedScale;
		Logger.log("<Box.setOwnedScale(Scale ownedScale)");
	}

	/**
	 * Lekeri a merleget, ami a dobozhoz tartozik
	 *
	 * @return
	 */
	public Scale getOwnedScale() {
		Logger.log(">Box.getOwnedScale()");
		Logger.log("<Box.getOwnedScale()");
		return ownedScale;
	}


	/**
	 * A lovedek talalkzoasa a dobozzal
	 *
	 * @param bullet
	 */
	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">Box.collideWith(Bullet bullet)");
		bullet.moveForward();
		Logger.log("<Box.collideWith(Bullet bullet)");
	}


	/**
	 * Az ezeredes kezenek a dobozzal valo talalkozasa
	 *
	 * @param hand
	 */

	@Override
	public void collideWith(ColonelsHand hand) {
		Logger.log(">Box.collideWith(ColonelsHand hand)");
		if (!hand.hasBox())
			hand.getColonel().boxPickUp(this);
		Logger.log("<Box.collideWith(ColonelsHand hand)");
	}
}
