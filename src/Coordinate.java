/**
 * 2D koordináták tárolására alkalmas osztály
 */

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		Logger.log(">Coordinate.Coordinate(int x, int y)");
		this.x = x;
		this.y = y;
		Logger.log("<Coordinate.Coordinate(int x, int y)");
	}

	public Coordinate(Coordinate coordinate) {
		Logger.log(">Coordinate.Coordinate(Coordinate coordinate)");
		x = coordinate.getX();
		y = coordinate.getY();
		Logger.log("<Coordinate.Coordinate(Coordinate coordinate)");
	}

	public Coordinate add(Coordinate coordinate) {
		Logger.log(">Coordinate.add(Coordinate coordinate)");
		Logger.log("<Coordinate.add(Coordinate coordinate)");
		return new Coordinate(this.x + coordinate.getX(), this.y + coordinate.getY());
	}

	public boolean equals(Coordinate coordinate) {
		Logger.log(">Coordinate.equals(Coordinate coordinate)");
		Logger.log("<Coordinate.equals(Coordinate coordinate)");
		return (x == coordinate.getX()) && (y == coordinate.getY());
	}

	public int getX() {
		Logger.log(">Coordinate.getX()");
		Logger.log("<Coordinate.getX()");
		return x;
	}

	public int getY() {
		Logger.log(">Coordinate.getY()");
		Logger.log("<Coordinate.getY()");
		return y;
	}

	public void setX(int x) {
		Logger.log(">Coordinate.setX(int x)");
		this.x = x;
		Logger.log("<Coordinate.setX(int x)");
	}

	public void setY(int y) {
		Logger.log(">Coordinate.setY(int y)");
		this.y = y;
		Logger.log("<Coordinate.setY(int y)");
	}
}
