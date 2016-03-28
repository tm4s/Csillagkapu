/**
 * Speciális fal
 */
public class SpecialWall extends Wall {
	/**
	 * Konstruktor
	 */
	public SpecialWall(){
		Logger.log(">SpecialWall.SpecialWall()");
		Logger.log("<SpecialWall.SpecialWall()");
	}

	/**
	 * A specialis fal egy lovedekkel valo talalkozast kovetoen csillagkapuva alakul
	 * @param bullet a specialis fallal talalkozo lovedek
     */
	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">SpecialWall.collideWith(Bullet bullet)");
		bullet.transformToTeleporter();
		Logger.log("<SpecialWall.collideWith(Bullet bullet)");
	}
}
