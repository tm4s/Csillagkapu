/**
 * 2D koordináták tárolására alkalmas osztály
 */

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(int, int)");
		this.x = x;
		this.y = y;
	}

	public Coordinate(Coordinate coordinate) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Coordinate)");
		x = coordinate.getX();
		y = coordinate.getY();
	}

	public Coordinate add(Coordinate coordinate) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Coordinate): Coordinate");
		return new Coordinate(this.x + coordinate.getX(), this.y + coordinate.getY());
	}

	public boolean equals(Coordinate coordinate) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(Coordinate): boolean");
		return (x == coordinate.getX()) && (y == coordinate.getY());
	}

	public int getX() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): int");
		return x;
	}

	public int getY() {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(): int");
		return y;
	}

	public void setX(int x) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(int): void");
		this.x = x;
	}

	public void setY(int y) {
		System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "(int): void");
		this.y = y;
	}
}
