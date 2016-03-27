/**
 * Doboz
 */

public class Box extends Field {

	/**
	 * es merleg akit ertesitenie kell ha felveszik rola
	 */
	private Scale ownedScale = null;

	public void setOwnedScale(Scale ownedScale) {
		Logger.log(">Box.setOwnedScale(Scale ownedScale)");
		this.ownedScale = ownedScale;
		Logger.log("<Box.setOwnedScale(Scale ownedScale)");
	}

	public Scale getOwnedScale() {
		Logger.log(">Box.getOwnedScale()");
		Logger.log("<Box.getOwnedScale()");
		return ownedScale;
	}

	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">Box.collideWith(Bullet bullet)");
		bullet.moveForward();
		Logger.log("<Box.collideWith(Bullet bullet)");
	}

	@Override
	public void collideWith(ColonelsHand hand) {
		Logger.log(">Box.collideWith(ColonelsHand hand)");
		if (!hand.hasBox())
			hand.getColonel().boxPickUp(this);
		Logger.log("<Box.collideWith(ColonelsHand hand)");
	}
}
