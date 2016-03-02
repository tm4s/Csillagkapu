/**
 * Pálya aktuális állásának tárolására való osztály
 */

public class Map {
    private int width;
    private int height;
    private Coordinate colonelPosition;
    private Coordinate bulletPosition = new Coordinate(-1,-1);

    private Field[][] data;

    public Map(String fileName) {

        Coordinate size = getMapSize(fileName);
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

    private Coordinate getMapSize(String fileName) {

        // kikene olvasni a palya meretet a fajlbol

        Coordinate size = new Coordinate(20, 10);     // csakohogy legyen valami

        return size;

    }

    private Coordinate getColonelStartPosition(String fileName) {

        // kikene olvasni hol az ezredes

        Coordinate pos = new Coordinate(1, 1);        // csakohogy legyen valami
        return  pos;
    }

    private void readMapData(String fileName) {

        // data tabla feltoltese adatokkal


    }

    public Field getFieldAt(Coordinate position) {
        return data[position.getY()][position.getX()];
    }
    public void setFieldAt(Coordinate position, Field field) {
        data[position.getY()][position.getX()] = field;
    }
    public Coordinate getColonelPosition() {
        return colonelPosition;
    }
}
