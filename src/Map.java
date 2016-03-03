import java.io.BufferedReader;

/**
 * Pálya aktuális állásának tárolására való osztály
 */

public class Map {
    private int width;
    private int height;
    private Coordinate colonelStartingPosition;
    private Coordinate bulletPosition = new Coordinate(-1,-1);

    private Field[][] data;

    public Map(String fileName) {

        Coordinate size = getMapSize(fileName);
        colonelStartingPosition = new Coordinate(getColonelStartPosition(fileName));

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

        // teszt adat
        Coordinate size = new Coordinate(5, 5);

        return size;

    }

    private Coordinate getColonelStartPosition(String fileName) {

        // kikene olvasni hol az ezredes

        //teszt adat
        Coordinate pos = new Coordinate(2, 2);
        return  pos;
    }

    private void readMapData(String fileName) {



        //teszt adat
        for (int y = 0; y < height; ++y){
            for (int x = 0; x < width; ++x){
                data[y][x] = new Wall();
            }
        }
        for (int y = 1; y < height-1; ++y){
            for (int x = 1; x < width-1; ++x){
                data[y][x] = new EmptyField();
            }
        }

        data[3][2] = new Box(null);
    }

    public Field getFieldAt(Coordinate position) {
        return data[position.getY()][position.getX()];
    }
    public void setFieldAt(Coordinate position, Field field) {
        data[position.getY()][position.getX()] = field;
    }
    public Coordinate getColonelStartingPosition() {
        return colonelStartingPosition;
    }
}
