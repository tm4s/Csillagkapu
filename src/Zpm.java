/**
 * ZPM modul
 */
public class Zpm extends Field {
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
    public Character print() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Character");
		return 'Z';
    }
}
