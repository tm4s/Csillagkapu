/**
 * Doboz
 */

public class Box extends Field {

	/**
	 * es merleg akit ertesitenie kell ha felveszik rola
	 */
	private Scale ownedScale = null;

	public void setOwnedScale(Scale ownedScale) {
		Logger.Log(">Box.setOwnedScale(Scale ownedScale)");
		this.ownedScale = ownedScale;
		Logger.Log("<Box.setOwnedScale(Scale ownedScale)");
	}

	public Scale getOwnedScale() {
		Logger.Log(">Box.getOwnedScale()");
		Logger.Log("<Box.getOwnedScale()");
		return ownedScale;
	}

	@Override
	public void collideWith(Bullet bullet) {
		Logger.Log(">Box.collideWith(Bullet bullet)");
		bullet.moveForward();
		Logger.Log("<Box.collideWith(Bullet bullet)");
	}

	@Override
	public void collideWith(ColonelsHand hand) {
		Logger.Log(">Box.collideWith(ColonelsHand hand)");
		if (!hand.hasBox())
			hand.getColonel().boxPickUp(this);
		Logger.Log("<Box.collideWith(ColonelsHand hand)");
	}

	@Override
	public Character print() {
		Logger.Log(">Box.print()");
		Logger.Log("<Box.print()");
		return 'B';
	}
}
