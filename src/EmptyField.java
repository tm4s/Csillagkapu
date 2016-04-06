/**
 * Üres mező
 */

public class EmptyField extends Field {

	@Override
	public void collideWith(Colonel colonel) {
		if (!isThereAColonel)
			colonel.moveTo(this);
	}

	@Override
	public void collideWith(Bullet bullet) {
		bulletMoveForward(bullet);
	}

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
