/**
 * 2D koordináták tárolására alkalmas osztály
 */

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate) {
        x = coordinate.getX();
        y = coordinate.getY();
    }

    public Coordinate add(Coordinate coordinate) {
        return new Coordinate(this.x + coordinate.getX(), this.y + coordinate.getY());
    }

    public  boolean equals(Coordinate coordinate) {
        return (x == coordinate.getX()) && (y == coordinate.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
