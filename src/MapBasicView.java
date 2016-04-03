import com.sun.org.apache.regexp.internal.RE;

/**
 * teszteleshez kezeli a map megjeleniteset
 */

public class MapBasicView {
	private Map map;
	private Colonel colonel;
	private Replicator replicator;
	private Colonel jaffa;

	public MapBasicView(Map map, Colonel colonel, Replicator replicator, Colonel jaffa) {
		this.map = map;
		this.colonel = colonel;
		this.replicator = replicator;
		this.jaffa = jaffa;
	}

	public void printMap() {
		replicator.move();
		for (int y = 0; y < map.getHeight(); ++y) {
			for (int x = 0; x < map.getWidth(); ++x) {
				if (colonel.getOwnedField().getPosition().equals(new Coordinate(y, x))) {
					System.out.print(printColonel(colonel));
				} else if (jaffa.getOwnedField().getPosition().equals(new Coordinate(y, x))) {
					System.out.print(printJaffa(jaffa));
				} else if (replicator.getOwnedField().getPosition().equals(new Coordinate(y, x))) {
					System.out.print(printColonel(replicator));
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

	private Character printColonel(Colonel colonel) {
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

	private Character printJaffa(Colonel jaffa) {
		Character c = '↑';
		switch (jaffa.getOrientation()) {
			case NORTH:
				c = '⥣';
				break;
			case WEST:
				c = '⥢';
				break;
			case SOUTH:
				c = '⥥';
				break;
			case EAST:
				c = '⥤';
				break;
		}
		return c;
	}
}
