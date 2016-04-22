/**
 * ZPM modul
 */
public class Zpm extends Field {
	static int allZPMs = 0;

	/**
	 * Konstruktor
	 */
	public Zpm() {
		allZPMs++;
	}

	public static int getAllZpms() {
		return allZPMs;
	}

	/**
	 * Az ezredes a ZPM modullal talalkozva begyujti azt
	 * A modul helyen egy ures mezo keletkezik
	 *
	 * @param colonel a ZPM modullal talalkozo ezredes
	 */
	@Override
	public void collideWith(Colonel colonel) {
		colonel.moveTo(this);
	}

	/**
	 * Egy lovedek ZPM modullal valo talalkozasakor a lovedek athalad a modulon
	 *
	 * @param bullet a ZPM modullal tallakozo lovedek
	 */
	@Override
	public void collideWith(Bullet bullet) {
		bullet.moveForward();
	}

	public void setNewPosition(Field startField) {
		Field actualField = startField;
		for (int i = 0; i < RandomGenerator.generateDistance(); ++i) {
			actualField = actualField.getNextRandomField();
		}
		actualField.collideWith(this);
	}

	@Override
	public void view(Controller controller) {
		controller.showView(this);
	}
}
