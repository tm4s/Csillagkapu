
public class Map {
    private int width;
    private int height;

    private Field[][] data = new Field[height][width];

    public Map (Size size, String fileName) {
        width = size.getX();
        height = size.getY();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
