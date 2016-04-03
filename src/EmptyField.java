/**
 * Üres mező
 */

public class EmptyField extends Field {

	@Override
	public void collideWith(Colonel colonel) {
		colonel.moveTo(this);
	}

	@Override
	public void collideWith(Bullet bullet) {
		bullet.moveForward();
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
