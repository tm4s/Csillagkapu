/**
 * Szakadék
 */
public class Ravine extends Field {

	/**
	 * Az ezredes szakadekba lepesenek a kezelese
	 *
	 * @param colonel
	 */
	@Override
	public void collideWith(Colonel colonel) {
		colonel.moveTo(this);
	}

	/**
	 * Egy szakadek es egy lovedek talalkozasa.
	 * A lovedek tovabbhalad a szakadek felett.
	 *
	 * @param bullet
	 */
	@Override
	public void collideWith(Bullet bullet) {
		bullet.moveForward();
	}

	/**
	 * Amennyiben az ezerdes kezeben doboz volt, az beleesik a szakadekba, es megszunik
	 *
	 * @param hand
	 */
	@Override
	public void collideWith(ColonelsHand hand) {
		if (hand.hasBox())
			hand.getColonel().boxPutDownToRavine(this);
	}

	@Override
	public void view(Controller controller) {
		controller.showView(this);
	}
}
