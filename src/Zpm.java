/**
 * ZPM modul
 */
public class Zpm extends Field {
	@Override
	public void collideWith(Colonel colonel) {
		Logger.log(">Zpm.collideWith(Colonel colonel");
		colonel.moveTo(this);
		Logger.log("<Zpm.collideWith(Colonel colonel");
	}

	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">Zpm.collideWith(Bullet bullet");
		bullet.moveForward();
		Logger.log("<Zpm.collideWith(Bullet bullet");
	}
	
	public Zpm(){
		Logger.log(">Zpm.collideWith(Bullet bullet");
		Logger.log("<Zpm.collideWith(Bullet bullet");
	}
}
