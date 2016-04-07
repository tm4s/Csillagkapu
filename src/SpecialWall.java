/**
 * Speciális fal
 */
public class SpecialWall extends Wall {

	/**
	 * A specialis fal egy lovedekkel valo talalkozast kovetoen csillagkapuva alakul
	 *
	 * @param bullet a specialis fallal talalkozo lovedek
	 */
	@Override
	public void collideWith(Bullet bullet) {
		bullet.transformToTeleporter();
	}

	@Override
	public Character print() {
		return '+';
	}
}
