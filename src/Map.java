
public class Map {
    private int width;
    private int height;

    private Field[][] data;

    public Map(String fileName) {

        Size size = getMapSize(fileName);
        width = size.getX();
        height = size.getY();

        data = new Field[height][width];

        readMapData(fileName);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private Size getMapSize(String fileName) {

        // kikene olvasni a palya meretet a fajlbol

        Size size = new Size(20, 10);     // csakohogy legyen valami

        return size;

    }

    private void readMapData(String fileName) {

        // data tabla feltoltese adatokkal

    }
}
