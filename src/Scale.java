/**
 * Mérleg
 */

public class Scale extends Field {
	private Door door = new Door();

	public Scale(Door door) {
		this.door = door;
	}

	@Override
	public void collideWith(Colonel colonel) {
		colonel.moveTo(this);
	}

	@Override
	public void collideWith(Bullet bullet) {
		bulletMoveForward(bullet);
	}

	@Override
	public void collideWith(ColonelsHand hand) {
		if (hand.hasBox())
			hand.getColonel().boxPutDownToScale(this);
	}

	@Override
	public Character print() {
		return 'S';
	}

	public void addWeight() {
		door.open();
	}

	public void removeWeight() {
		door.close();
	}

}
