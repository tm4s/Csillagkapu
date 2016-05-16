/**
 * Irány tárolására alkalmas osztály
 */

public class Orientation {
	/**
	 * Az iranyok tipusai
	 */
	public enum Type {
		NORTH, WEST, SOUTH, EAST
	}

	/**
	 * Visszaadja az ellenkezo iranyt
 	 * @param i
	 * @return
     */
    public static Type getOpposite(int i) {
		Type direction = Type.NORTH;
		switch (i) {
		case 0:
			direction = Type.SOUTH;
			break;
		case 1:
			direction = Type.EAST;
			break;
		case 2:
			direction = Type.NORTH;
			break;
		case 3:
			direction = Type.WEST;
			break;
		default:
			break;
		}
		return direction;
	}

}
