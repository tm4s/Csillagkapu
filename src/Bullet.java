/**
 * Lövedék
 */

public class Bullet {

	/**
	 * aktualis pozocio, irany tipusa a teleporternek ami letrejohet belole
	 */
	private Field ownedField;
	private Orientation.Type direction;
	private Teleporter.Type type;

	/**
	 * konstruktor
	 *
	 * @param type       telepotrter tipusa
	 * @param startField kezdo mezo
	 * @param direction  haladasi irany
	 */
	public Bullet(Teleporter.Type type, Field startField, Orientation.Type direction) {
		Logger.log(">Bullet.Bullet(Teleporter.Type type, Field startField, Orientation.Type direction)");
		this.type = type;
		ownedField = startField;
		this.direction = direction;
		Logger.log("<Bullet.Bullet(Teleporter.Type type, Field startField, Orientation.Type direction)");
	}

	/**
	 * tovabb halad a kovetkezo mezore mezotol amin all elkeri a megfelelo
	 * iranyu szomszedjat es utkozik vele
	 */
	public void moveForward() {
		Logger.log(">Bullet.moveForward()");
		ownedField = ownedField.getNextField(direction);
		ownedField.collideWith(this);
		Logger.log("<Bullet.moveForward()");
	}

	/**
	 * atalkulas teleporterre a mezon amin all ott letrehoz egy teleportert
	 * (regi mezo eltunik)
	 */
	public void transformToTeleporter() {
		Logger.log(">Bullet.transformToTeleporter()");
		ownedField.setField(new Teleporter(type, direction));
		Logger.log("<Bullet.transformToTeleporter()");
	}
}
