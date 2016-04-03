import com.sun.org.apache.regexp.internal.RE;

/**
 * teszteleshez kezeli a map megjeleniteset
 */

public class MapBasicView {
	private Map map;
	private Colonel colonel;
	private Replicator replicator;

	public MapBasicView(Map map, Colonel colonel, Replicator replicator) {
		this.map = map;
		this.colonel = colonel;
		this.replicator = replicator;
	}

	public void printMap() {
		replicator.move();
		for (int y = 0; y < map.getHeight(); ++y) {
			for (int x = 0; x < map.getWidth(); ++x) {
				if (colonel.getOwnedField().getPosition().equals(new Coordinate(y, x))) {
					System.out.print(printColonel());
				} else if (replicator.getOwnedField().getPosition().equals(new Coordinate(y, x))) {
					System.out.print(printColonel());
				} else {
					System.out.print(map.getFieldAt(new Coordinate(y, x)).print());
				}
				// System.out.print(' ');
			}
			System.out.println();
			// System.out.println();
		}
		System.out.println();
		System.out.println("ZPM = " + colonel.getCollectedZpms() + " / " + Zpm.getAllZpms());
		System.out.println();
	}

	private Character printColonel() {
		Character c = 'A';
		switch (colonel.getOrientation()) {
		case NORTH:
			c = 'A';
			break;
		case WEST:
			c = '<';
			break;
		case SOUTH:
			c = 'V';
			break;
		case EAST:
			c = '>';
			break;
		}
		return c;
	}
}
