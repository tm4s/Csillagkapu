/**
 * ZPM modul
 */
public class Zpm extends Field {
	/**
	 * Konstruktor
	 */
	public Zpm() {
		Logger.log(">Zpm.collideWith(Bullet bullet");
		Logger.log("<Zpm.collideWith(Bullet bullet");
	}

	/**
	 * Az ezredes a ZPM modullal talalkozva begyujti azt
	 * A modul helyen egy ures mezo keletkezik
	 *
	 * @param colonel a ZPM modullal talalkozo ezredes
	 */
	@Override
	public void collideWith(Colonel colonel) {
		Logger.log(">Zpm.collideWith(Colonel colonel");
		colonel.moveTo(this);
		Logger.log("<Zpm.collideWith(Colonel colonel");
	}

	/**
	 * Egy lovedek ZPM modullal valo talalkozasakor a lovedek athalad a modulon
	 *
	 * @param bullet a ZPM modullal tallakozo lovedek
	 */
	@Override
	public void collideWith(Bullet bullet) {
		Logger.log(">Zpm.collideWith(Bullet bullet");
		bullet.moveForward();
		Logger.log("<Zpm.collideWith(Bullet bullet");
	}
}
