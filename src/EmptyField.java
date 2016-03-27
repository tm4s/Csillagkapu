/**
 * Üres mező
 */

public class EmptyField extends Field {

	public EmptyField() {
		Logger.log(">EmptyField.EmptyField()");
		Logger.log("<EmptyField.EmptyField()");
	}
	@Override
	public void collideWith(Colonel colonel) {
		Logger.log(">EmptyField.collideWith(Colonel colonel)");
		colonel.moveTo(this);
		Logger.log("<EmptyField.collideWith(Colonel colonel)");
	}

	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">EmptyField.collideWith(Bullet bullet)");
		bullet.moveForward();
		Logger.log("<EmptyField.collideWith(Bullet bullet)");
	}

	@Override
	public void collideWith(ColonelsHand hand) {
		Logger.log(">EmptyField.collideWith(ColonelsHand hand)");
		if (hand.hasBox())
			hand.getColonel().boxPutDownToEmptyField(this);
		Logger.log("<EmptyField.collideWith(ColonelsHand hand)");
	}
}
