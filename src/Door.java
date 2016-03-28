/**
 * Ajtó
 */

public class Door extends Field {
	//Ajto belso valtozoja, hogy nyitva van-e
	private boolean isOpened;

	/**
	 * Konstruktor
	 */
	public Door() {
		Logger.log(">Door.Door()");
		isOpened = false;
		Logger.log("<Door.Door()");
	}

	/**
	 * Az ajto bezarodik
	 */
	public void open() {
		Logger.log(">Door.open()");
		isOpened = true;
		Logger.log("<Door.open()");
	}

	/**
	 * Az ajto kinyilik
	 */
	public void close() {
		Logger.log(">Door.close()");
		isOpened = false;
		Logger.log("<Door.close()");
	}

	/**
	 * Ha nyitva van az ajto az ezredes athalad rajta
	 *
	 * @param colonel
	 */
	@Override
	public void collideWith(Colonel colonel) {
		Logger.log(">Door.collideWith(Colonel colonel)");
		if (isOpened) {
			colonel.moveTo(this);
		}
		Logger.log("<Door.collideWith(Colonel colonel)");
	}

	/**
	 * Ha nyitva van az ajto a lovedek tovabbhalad rajta
	 *
	 * @param bullet
	 */
	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">Door.collideWith(Bullet bullet)");
		if (isOpened) {
			bullet.moveForward();
		}
		Logger.log("<Door.collideWith(Bullet bullet)");
	}
}
