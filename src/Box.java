/**
 * Doboz
 */

public class Box extends Field {

	/**
	 * es merleg akit ertesitenie kell ha felveszik rola
	 */
	private Scale ownedScale = null;

	public void setOwnedScale(Scale ownedScale) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Scale): void");
		this.ownedScale = ownedScale;
	}

	public Scale getOwnedScale() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Scale");
		return ownedScale;
	}


	@Override
	public void collideWith(Bullet bullet) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Bullet): void");
		bullet.moveForward();
	}


	@Override
	public void collideWith(ColonelsHand hand) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(ColonelsHand): void");
		if (!hand.hasBox())
			hand.getColonel().boxPickUp(this);
	}

	@Override
	public Character print() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Character");
		return 'B';
	}
}
