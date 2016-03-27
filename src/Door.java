/**
 * Ajtó
 */

public class Door extends Field {
	private boolean isOpened;
	
	public Door() {
		Logger.log(">Door.Door()");
		isOpened = false;
		Logger.log("<Door.Door()");
	}

	public void open() {
		Logger.log(">Door.open()");
		isOpened = true;
		Logger.log("<Door.open()");
	}

	public void close() {
		Logger.log(">Door.close()");
		isOpened = false;
		Logger.log("<Door.close()");
	}

	@Override
	public void collideWith(Colonel colonel) {
		Logger.log(">Door.collideWith(Colonel colonel)");
		if (isOpened) {
			colonel.moveTo(this);
		}
		Logger.log("<Door.collideWith(Colonel colonel)");
	}

	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">Door.collideWith(Bullet bullet)");
		if (isOpened) {
			bullet.moveForward();
		}
		Logger.log("<Door.collideWith(Bullet bullet)");
	}
}
