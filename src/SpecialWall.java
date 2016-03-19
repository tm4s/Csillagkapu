/**
 * Speciális fal
 */
public class SpecialWall extends Wall {

	@Override
	public void collideWith(Bullet bullet) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Bullet): void");
		bullet.transformToTeleporter();
	}

	@Override
	public Character print() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Character");
		return '+';
	}
}
