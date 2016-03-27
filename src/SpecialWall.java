/**
 * Speciális fal
 */
public class SpecialWall extends Wall {

	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">SpecialWall.collideWith(Bullet bullet)");
		bullet.transformToTeleporter();
		Logger.log("<SpecialWall.collideWith(Bullet bullet)");
	}
	
	public SpecialWall(){
		Logger.log(">SpecialWall.SpecialWall()");
		Logger.log("<SpecialWall.SpecialWall()");
	}
}
