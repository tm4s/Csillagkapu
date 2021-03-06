/**
 * Csillagkapu
 */
public class Teleporter extends Field {
	/**
	 * csillagkapuk tipusai
	 */
	enum Type {
		ORANGE, BLUE, RED, GREEN
	}

	/**
	 * palyan levo lerakott csillagkapuk static mivel csak 2 lehet es mivel
	 * tudniuk kell egymasrol palyan letrehozott csillagkapura tarol referenciat
	 */
	private static Teleporter[] Teleporters = new Teleporter[4];

	private Type type;
	private Orientation.Type orientation;

	/**
	 * konstruktor ha ugyanolyan tipusu csillagkapu mar van a palyan akkor annak
	 * a helyen letrehoz egy specialis falat (mivel a csillagkapu elott ennek
	 * kellett a helyen lennie) majd magara allitja a statikus vele megegyezo
	 * csillagkapu referenciat
	 * 
	 * @param type
	 *            csillagkapu tipusa (szine)
	 */
	public Teleporter(Type type, Orientation.Type orientation) {
		this.type = type;
		this.orientation = Orientation.getOpposite(orientation.ordinal());
		if (Teleporters[type.ordinal()] != null) {
			Teleporters[type.ordinal()].setField(new SpecialWall());
		}
		Teleporters[type.ordinal()] = this;
	}

	/**
	 * masik teleporter lekrese
	 * 
	 * @return ellenkezo tipusu csillagkapu lehet null csak olvashatosag
	 *         kedveert van
	 */
	private Teleporter getOtherTeleporter() {
		if (this.type == Type.ORANGE)  {
			return Teleporters[type.ordinal() + 1];
		}
		else if (this.type == Type.BLUE) {
			return Teleporters[type.ordinal() -1];
		} else if (this.type == Type.RED)  {
			return Teleporters[type.ordinal() + 1];
		}
		else {return Teleporters[type.ordinal() -1];
		}
	}

	public Teleporter.Type getType() {
		return type;
	}

	public Orientation.Type getOrientation() {
		return orientation;
	}

	/**
	 * Az ezredes belepve az egyik csillagkapun a masikon jon ki, amennyiben az nyitva van
	 *
	 * @param colonel
	 */
    @Override
    public void collideWith(Colonel colonel) {
		if (getOtherTeleporter() != null)
			colonel.teleportTo(getOtherTeleporter());
	}

	@Override
	public void view(Controller controller) {
		controller.showView(this);
	}
}
