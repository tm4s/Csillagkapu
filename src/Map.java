import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;
import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Pálya aktuális állásának tárolására való osztály
 */

public class Map {
    private int width;
    private int height;
    private Coordinate colonelStartingPosition;
    private Coordinate bulletPosition = new Coordinate(-1,-1);

    private Field[][] data;

    public Map(String fileName) throws IOException {
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



    private void readMapData(String fileName) throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(new File(fileName)));
        String line = "";

        //A palya meretenek beolvasasa
        line = br.readLine();
        String mapSize[] = line.split(";");
        width = Integer.parseInt(mapSize[0]);
        height = Integer.parseInt(mapSize[1]);


        //Uj palya letrehozasa
        data = new Field[height][width];

        int j = 0;

        //Egyes elemek beolvasasa
        while ((line = br.readLine()) != null) {
            String array[] = line.split(";");
            for (int i = 0; i < width; i++) {
                switch (array[i]) {
                    case "E":
                        data[j][i] = new EmptyField();
                        break;
                    case "W":
                        data[j][i] = new Wall();
                        break;
                    case "D":
                        data[j][i] = new Door();
                        break;
                    case "B":
                        data[j][i] = new Box(null);
                        break;
                    case "C":
                        data[j][i] = new EmptyField();
                        colonelStartingPosition = new Coordinate(j, i);
                        break;
                    case "S":
                        data[j][i] = new Scale();
                        break;
                    default:
                        break;
                }

            }
            j++;
        }


        br.close();


    }

    public Field getFieldAt(Coordinate position) {
        return data[position.getX()][position.getY()];
    }
    public void setFieldAt(Coordinate position, Field field) {
        data[position.getX()][position.getY()] = field;
    }
    public Coordinate getColonelStartingPosition() {
        return colonelStartingPosition;
    }
}
