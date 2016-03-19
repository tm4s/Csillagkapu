/**
 * Üres mező
 */

public class EmptyField extends Field {

	@Override
	public void collideWith(Colonel colonel) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Colonel): void");
		colonel.moveTo(this);
	}

	@Override
	public void collideWith(Bullet bullet) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Bullet): void");
		bullet.moveForward();
	}

	@Override
	public void collideWith(ColonelsHand hand) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(ColonelsHand): void");
		if (hand.hasBox())
			hand.getColonel().boxPutDownToEmptyField(this);
	}

	@Override
	public Character print() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		return ' ';
	}
}
