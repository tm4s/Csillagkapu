/**
 * Ajtó
 */

public class Door extends Field {
	//Ajto belso valtozoja, hogy nyitva van-e
	private boolean isOpened = false;

	/**
	 * Az ajto kinyilik
	 */
	public void open() {
		isOpened = true;
	}

	/**
	 * Az ajto bezarodik
	 */
	public void close() {
		isOpened = false;
	}


	/**
	 * Ha nyitva van az ajto, az ezredes athalad rajta
	 *
	 * @param colonel
	 */
	@Override
	public void collideWith(Colonel colonel) {
		if (isOpened && !isThereAColonel) {
			colonel.moveTo(this);
		}

	}

	/**
	 * Ha nyitva van az ajto, a lovedek tovabbhalad rajta
	 *
	 * @param bullet
	 */
	@Override
	public void collideWith(Bullet bullet) {
		if (isOpened) {
			bullet.moveForward();
		}

	}



	@Override
	public Character print() {
		Character c = 'D';
		if (isOpened)
			c = ' ';
		return c;
	}

}
