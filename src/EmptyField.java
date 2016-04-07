/**
 * Üres mező
 */

public class EmptyField extends Field {

	/**
	 * Az ezredes tovabbhalad a mezon
	 *
	 * @param colonel
	 */
	@Override
	public void collideWith(Colonel colonel) {
		if (!isThereAColonel)
			colonel.moveTo(this);
	}

	/**
	 * A lovedek tovabbhalad a mezon
	 *
	 * @param bullet
	 */
	@Override
	public void collideWith(Bullet bullet) {
		bulletMoveForward(bullet);
	}

	/**
	 * Ures mezorol torteno doboz felvetel, illetve arra tortene lerakas
	 *
	 * @param hand
	 */
	@Override
	public void collideWith(ColonelsHand hand) {
		if (hand.hasBox())
			hand.getColonel().boxPutDownToEmptyField(this);
	}

	@Override
	public void collideWith(Zpm zpm) {
		setField(zpm);
	}

	@Override
	public Character print() {
		return ' ';
	}
}
