/**
 * Irany tarolasara alkalmas osztaly
 */

// A grafikus interface keszitesenel majd figyelni kell ra

public class Orientation {
	public enum Type {
		NORTH, WEST, SOUTH, EAST
	}


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
