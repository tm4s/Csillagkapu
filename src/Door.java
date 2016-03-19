/**
 * Ajtó
 */

public class Door extends Field {
	private boolean isOpened = false;

	public void open() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): void");
		isOpened = true;
	}

	public void close() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): void");
		isOpened = false;
	}

	@Override
	public void collideWith(Colonel colonel) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Colonel): void");
		if (isOpened) {
			colonel.moveTo(this);
		}
	}

	@Override
	public void collideWith(Bullet bullet) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Bullet): void");
		if (isOpened) {
			bullet.moveForward();
		}
	}

	@Override
	public Character print() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Character");
		Character c = 'D';
		if (isOpened)
			c = ' ';
		return c;
	}
}
